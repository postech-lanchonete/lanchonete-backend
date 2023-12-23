package br.com.lanchonetebairro.integrationTest;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
import br.com.lanchonetebairro.enterpriserules.enums.StatusDoPedido;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.interfaceadapters.repositories.ClienteRepository;
import br.com.lanchonetebairro.interfaceadapters.repositories.PedidoRepository;
import br.com.lanchonetebairro.interfaceadapters.repositories.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase
class PedidoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        // Limpar dados de teste antes de cada execução de teste
        pedidoRepository.deleteAll();
    }

    @Test
    void buscarTodos_DeveRetornarListaDePedidos_QuandoExistiremPedidosNoBanco() throws Exception {
        Produto produtoHamburguer = criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        Produto produtoBatataFrita = criarProduto("Batata Frita", CategoriaProduto.ACOMPANHAMENTO, BigDecimal.valueOf(15.0));
        Cliente cliente = criarCliente("123456789");
        criarPedido(List.of(produtoHamburguer, produtoBatataFrita), cliente, StatusDoPedido.RECEBIDO);

        mockMvc.perform(get("/v1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void buscarTodos_DeveRetornarListaVazia_QuandoNaoExistiremPedidosNoBanco() throws Exception {
        mockMvc.perform(get("/v1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void buscarPorStatus_DeveRetornarListaDePedidosComStatusRecebido_QuandoExistiremPedidosComEsseStatus() throws Exception {
        Produto produtoHamburguer = criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        Produto produtoBatataFrita = criarProduto("Batata Frita", CategoriaProduto.ACOMPANHAMENTO, BigDecimal.valueOf(15.0));
        Cliente cliente = criarCliente("123456789");
        criarPedido(List.of(produtoHamburguer, produtoBatataFrita), cliente, StatusDoPedido.RECEBIDO);
        
        mockMvc.perform(get("/v1/pedidos?status=RECEBIDO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void buscarPorStatus_DeveRetornarListaDePedidosComStatusRecebido_QuandoNenhumPedidoComStatusRecebidoNoBanco() throws Exception {

        mockMvc.perform(get("/v1/pedidos?status=RECEBIDO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void buscarPorStatus_DeveRetornarBadRequest_QuandoStatusInvalido() throws Exception {
        mockMvc.perform(get("/v1/pedidos?status=XXX"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void criar_DeveRegistrarPedidoNoBanco_QuandoReceberDadosCorretos() throws Exception {
        Produto produtoHamburguer = criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        Produto produtoBatataFrita = criarProduto("Batata Frita", CategoriaProduto.ACOMPANHAMENTO, BigDecimal.valueOf(15.0));
        Cliente cliente = criarCliente("123456789");

        CriacaoPedidoDTO pedidoDTO = new CriacaoPedidoDTO();
        pedidoDTO.setCpfCliente(cliente.getCpf());
        pedidoDTO.setIdsProdutos(Arrays.asList(produtoHamburguer.getId(), produtoBatataFrita.getId()));

        mockMvc.perform(post("/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pedidoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.produtos", hasSize(2)))
                .andExpect(jsonPath("$.produtos.[0].nome", is("Hambúrguer")))
                .andExpect(jsonPath("$.cliente.nome", is("Teste")))
                .andExpect(jsonPath("$.status", is("RECEBIDO")))
                .andExpect(jsonPath("$.dataCriacao").exists());
    }

    @Test
    void criar_DeveFalharAoRegistrarPedidoNoBanco_QuandoReceberPedidosInexistentes() throws Exception {
        String cpf = "123456789";
        criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        criarProduto("Batata Frita", CategoriaProduto.ACOMPANHAMENTO, BigDecimal.valueOf(15.0));
        criarCliente(cpf);

        CriacaoPedidoDTO pedidoDTO = new CriacaoPedidoDTO();
        pedidoDTO.setCpfCliente(cpf);
        pedidoDTO.setIdsProdutos(Arrays.asList(3L, 4L));

        mockMvc.perform(post("/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pedidoDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void criar_DeveFalharAoRegistrarPedidoNoBanco_QuandoReceberClienteInexistente() throws Exception {
        String cpf = "123456789";
        Produto produtoHamburguer = criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        Produto produtoBatataFrita = criarProduto("Batata Frita", CategoriaProduto.ACOMPANHAMENTO, BigDecimal.valueOf(15.0));
        criarCliente(cpf);

        CriacaoPedidoDTO pedidoDTO = new CriacaoPedidoDTO();
        pedidoDTO.setCpfCliente(cpf + "XXX");
        pedidoDTO.setIdsProdutos(new ArrayList<>(Arrays.asList(produtoHamburguer.getId(), produtoBatataFrita.getId())));

        mockMvc.perform(post("/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pedidoDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void mudarStatus_DeveAtualizarStatusParaEmPreparacao_QuandoPedidoExistirEStatusForRecebido() throws Exception {
        Produto produtoHamburguer = criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        Cliente cliente = criarCliente("123456789");
        Pedido pedido = criarPedido(new ArrayList<>(List.of(produtoHamburguer)), cliente, StatusDoPedido.RECEBIDO);

        mockMvc.perform(put("/v1/pedidos/{id}/status", pedido.getId()))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("EM_PREPARACAO")));
    }

    @Test
    void mudarStatus_DeveAtualizarStatusParaPronto_QuandoPedidoExistirEStatusForEmPreparacao() throws Exception {
        Produto produtoHamburguer = criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        Cliente cliente = criarCliente("123456789");
        Pedido pedido = criarPedido(new ArrayList<>(List.of(produtoHamburguer)), cliente, StatusDoPedido.EM_PREPARACAO);
        Long id = pedido.getId();
        mockMvc.perform(put(String.format("/v1/pedidos/%d/status", id)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("PRONTO")));
    }
    @Test
    @Transactional
    void mudarStatus_DeveAtualizarStatusParaFinalizado_QuandoPedidoExistirEStatusForPronto() throws Exception {
        Produto produtoHamburguer = criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        Cliente cliente = criarCliente("123456789");
        Pedido pedido = criarPedido(new ArrayList<>(List.of(produtoHamburguer)), cliente, StatusDoPedido.PRONTO);

        mockMvc.perform(put("/v1/pedidos/{id}/status", pedido.getId()))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("FINALIZADO")));
    }

    @Test
    void mudarStatus_DeveManterStatusFinalizado_QuandoPedidoExistirEStatusForFinalizado() throws Exception {
        Produto produtoHamburguer = criarProduto("Hambúrguer", CategoriaProduto.LANCHE, BigDecimal.valueOf(32.5));
        Cliente cliente = criarCliente("123456789");
        Pedido pedido = criarPedido(new ArrayList<>(List.of(produtoHamburguer)), cliente, StatusDoPedido.FINALIZADO);

        mockMvc.perform(put("/v1/pedidos/{id}/status", pedido.getId()))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("FINALIZADO")));
    }

    private static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Produto criarProduto(String nome, CategoriaProduto categoria, BigDecimal preco) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setCategoria(categoria);
        produto.setPreco(preco);
        produto.setDescricao("Lorem ipsum dolor sit amet.");
        produto.setImagem("");
        return produtoRepository.save(produto);
    }

    private Cliente criarCliente(String cpf) {
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        cliente.setSobrenome("Silva");
        cliente.setCpf(cpf);
        cliente.setEmail("test@test.teste");
        cliente.setSenha("admin");
        return clienteRepository.save(cliente);
    }

    private Pedido criarPedido(List<Produto> pedidos, Cliente cliente, StatusDoPedido status) {
        Pedido pedido = new Pedido();
        pedido.setStatus(status);
        pedido.setCliente(cliente);
        pedido.setProdutos(pedidos);
        return pedidoRepository.save(pedido);
    }
}
