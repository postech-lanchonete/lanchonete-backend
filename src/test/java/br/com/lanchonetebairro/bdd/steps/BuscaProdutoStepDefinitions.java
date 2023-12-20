//package br.com.lanchonetebairro.bdd.steps;
//
//import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
//import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoProdutoDTO;
//import br.com.lanchonetebairro.interfaceadapters.dto.ProdutoResponseDTO;
//import io.cucumber.java.pt.Dado;
//import io.cucumber.java.pt.E;
//import io.cucumber.java.pt.Então;
//import io.cucumber.java.pt.Quando;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//public class BuscaProdutoStepDefinitions {
//
//    private final String baseUrl = "http://localhost:8080";
//    private final RestTemplate restTemplate = new RestTemplate();
//    private Long idProduto;
//    private String categoriaProduto;
//    private String parametroBusca;
//
//    private List<ProdutoResponseDTO> produtosEncontrados;
//    private ProdutoResponseDTO produtoEncontrado;
//
//    HttpStatusCode statusCode;
//    String responseBody;
//
//    @Dado("que um usuário queira buscar todos os produtos")
//    public void queUmUsuarioQueiraBuscarTodosOsProdutos() {
//        this.categoriaProduto = "";
//    }
//    @Dado("que um usuário queira buscar todos os produtos da categoria {string}")
//    public void queUmUsuarioQueiraBuscarTodosOsProdutosDaCategoria(String categoria) {
//        this.categoriaProduto = categoria;
//    }
//
//    @Dado("que um usuário queira buscar todos pelo parametro {string} chamado {string}")
//    public void queUmUsuarioQueiraBuscarTodos(String parametro, String valor) {
//        this.parametroBusca = "?" + parametro + "=" + valor;
//    }
//
//    @Dado("que um usuário queira buscar o produto com o ID {int}")
//    public void queUmUsuarioQueiraBuscarOProdutoComOID(long idProduto) {
//        this.idProduto = idProduto;
//    }
//
//    @Quando("o usuário enviar uma requisição GET para {string} e o ID do produto")
//    public void oUsuárioEnviarUmaRequisicaoGETParaEOIDDoProduto(String patch) {
//        try {
//            ResponseEntity<ProdutoResponseDTO> response = restTemplate.getForEntity(baseUrl + patch + this.idProduto, ProdutoResponseDTO.class);
//
//            HttpStatusCode statusCode = response.getStatusCode();
//            ProdutoResponseDTO produtoResponseDTO = response.getBody();
//            this.statusCode = statusCode;
//            this.produtoEncontrado = produtoResponseDTO;
//        } catch (HttpClientErrorException e) {
//            HttpStatusCode statusCode = e.getStatusCode();
//            String responseBody = e.getResponseBodyAsString();
//            this.statusCode = statusCode;
//            this.responseBody = responseBody;
//        }
//    }
//
//    @Quando("o usuário enviar uma requisição GET para {string} com a categoria desejada")
//    public void usuarioEnviaRequisicaoComDadosDoProduto(String patch) {
//        try {
//            ResponseEntity<List<ProdutoResponseDTO>> response = restTemplate.exchange(
//                    baseUrl + patch + parametroBusca,
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<>() {}
//            );
//            HttpStatusCode statusCode = response.getStatusCode();
//            List<ProdutoResponseDTO> produtoCriado = response.getBody();
//            this.statusCode = statusCode;
//            this.produtosEncontrados = produtoCriado;
//        } catch (HttpClientErrorException e) {
//            HttpStatusCode statusCode = e.getStatusCode();
//            String responseBody = e.getResponseBodyAsString();
//            this.statusCode = statusCode;
//            this.responseBody = responseBody;
//        }
//    }
//
//    @Então("o resultado da busca deve conter um produto com nome {string} com o preço {bigdecimal}")
//    public void oResultadoDaBuscaDeveConterUmProdutoComNomeComOPreco(String nome, BigDecimal preco) {
//        assertNotNull(produtosEncontrados);
//
//        boolean produtoEncontrado = produtosEncontrados.stream()
//                .anyMatch(produto -> produto.getNome().equals(nome) && produto.getPreco().compareTo(preco) == 0);
//
//        assertTrue("Produto não encontrado com nome " + nome + " e preço " + preco, produtoEncontrado);
//    }
//
//    @Então("o resultado da busca não deve conter nenhum produto")
//    public void oResultadoDaBuscaNaoDeveConterNenhumProduto() {
//        assertNotNull(produtosEncontrados);
//        assertEquals(0, produtosEncontrados.size());
//    }
//
//    @Então("o resultado da busca deve conter o produto com nome {string} com o preço {bigdecimal}")
//    public void oResultadoDaBuscaDeveConterOProdutoComNomeComOPreco(String nome, BigDecimal preco) {
//        assertNotNull(produtoEncontrado);
//        assertEquals(nome, produtoEncontrado.getNome());
//        assertEquals(preco, produtoEncontrado.getPreco());
//    }
//
//    @Então("o resultado da busca deve ser vazio")
//    public void oResultadoDaBuscaDeveSerVazio() {
//        assertNull(produtoEncontrado);
//    }
//
//    @Então("o resultado da busca deve ser uma lista vazia")
//    public void oResultadoDaBuscaDeveSerUmaListaVazia() {
//        assertNotNull(produtosEncontrados);
//        assertEquals(0, produtosEncontrados.size());
//    }
//
//    @E("o status deve ser {int}")
//    @Então("o status deve ser {int}")
//    public void oStatusDeveSer(int statusEsperado) {
//        assertEquals(statusCode.value(), statusEsperado);
//    }
//
//    @E("conter um erro da mensagem contendo {string}")
//    public void conterUmErroDaMensagemContendo(String mensagemErro) {
//        assertTrue(responseBody.contains(mensagemErro));
//    }
//
//
//}
