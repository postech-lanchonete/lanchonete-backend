package br.com.lanchonetebairro.bdd.steps;

import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.ProdutoResponseDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ProdutoStepDefinition {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "http://localhost:8080";
    private String parameters;


    private ProdutoResponseDTO produtoCriado;
    private CriacaoProdutoDTO produtoDTO;
    private Long idProduto;
    private String categoriaProduto;
    private String parametroBusca;

    private List<ProdutoResponseDTO> produtosEncontrados;
    private ProdutoResponseDTO produtoEncontrado;

    HttpStatusCode statusCode;
    String responseBody;

    @Dado("que exista um produto com id {long} e o atributo {string} diferente a {string}")
    public void queExistaUmProdutoComIdEOAtributoDiferenteA(Long id, String atributo, String valor) {
        try {
            ResponseEntity<ProdutoResponseDTO> response = restTemplate.getForEntity(baseUrl + "/v1/produtos/" + id, ProdutoResponseDTO.class);
            ProdutoResponseDTO produtoCriado = response.getBody();
            Object atributoProduto = buscarAtributoProduto(atributo, produtoCriado);
            assertNotEquals(valor, atributoProduto);
        } catch (HttpClientErrorException e) {
            CriacaoProdutoDTO produtoDTO = new CriacaoProdutoDTO("Produto",  CategoriaProduto.BEBIDA, BigDecimal.valueOf(10),"Descrição", "Imagem");
            restTemplate.postForEntity(baseUrl + "/v1/produtos", produtoDTO, ProdutoResponseDTO.class);
        }
    }

    @Dado("que um usuário queira cadastrar um produto com nome {string} e preço {bigdecimal} e categoria {string}")
    public void usuarioQuerCadastrarProdutoComNomeEPreco(String nomeProduto, BigDecimal precoProduto, String categoriaProduto) {
        this.produtoDTO = criacaoProdutoDto(nomeProduto, precoProduto, categoriaProduto);
    }

    @Dado("que um usuário queira buscar todos os produtos")
    public void queUmUsuarioQueiraBuscarTodosOsProdutos() {
        this.parameters = "/";
    }

    @Dado("que um usuário queira buscar todos os produtos da categoria {string}")
    public void queUmUsuarioQueiraBuscarTodosOsProdutosDaCategoria(String categoria) {
        this.parameters = "/?categoria="+categoria;
    }

    @Dado("que um usuário queira buscar todos pelo parametro {string} chamado {string}")
    public void queUmUsuarioQueiraBuscarTodos(String parametro, String valor) {
        this.parametroBusca = "?" + parametro + "=" + valor;
    }

    @Dado("que um usuário queira buscar o produto com o ID {int}")
    public void queUmUsuarioQueiraBuscarOProdutoComOID(long idProduto) {
        this.idProduto = idProduto;
    }


    @Quando("o usuário enviar uma requisição PUT para {string} com o atributo {string} igual a {string} para o id {long}")
    public void oUsuárioEnviarUmaRequisicaoPUTParaComOAtributoIgualAParaOId(String patch, String atributo, String valor, Long id) {
        CriacaoProdutoDTO produtoRequest = criarProduto(atributo, valor);

        try {
            ResponseEntity<ProdutoResponseDTO> response = restTemplate.exchange(baseUrl + patch + id, HttpMethod.PUT, new HttpEntity<>(produtoRequest), ProdutoResponseDTO.class);

            HttpStatusCode statusCode = response.getStatusCode();
            ProdutoResponseDTO produtoCriado = response.getBody();
            this.statusCode = statusCode;
            this.produtoCriado = produtoCriado;
        } catch (HttpClientErrorException e) {
            this.statusCode = e.getStatusCode();
        }
    }

    @Então("o resultado da busca deve conter um produto com atributo {string} igual a {string}")
    public void oResultadoDaBuscaDeveConterUmProdutoComAtributoIgualA(String atributo, String valor) {
        if(atributo.equals("preco")) {
            BigDecimal valorInserido = (BigDecimal) buscarAtributoProduto(atributo, produtoCriado);
            assertEquals(0, new BigDecimal(valor).compareTo(valorInserido));
        } else {
            assertEquals(valor, buscarAtributoProduto(atributo, produtoCriado));
        }
    }



    @Quando("o usuário enviar uma requisição POST para {string} com os dados do produto")
    public void usuarioEnviaRequisicaoPostComDadosDoProduto(String patch) {
        try {
            ResponseEntity<ProdutoResponseDTO> response = restTemplate.postForEntity(baseUrl + patch, this.produtoDTO, ProdutoResponseDTO.class);

            HttpStatusCode statusCode = response.getStatusCode();
            ProdutoResponseDTO produtoCriado = response.getBody();
            this.statusCode = statusCode;
            this.produtoCriado = produtoCriado;
        } catch (HttpClientErrorException e) {
            HttpStatusCode statusCode = e.getStatusCode();
            String responseBody = e.getResponseBodyAsString();
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }
    }




    @Quando("o usuário enviar uma requisição GET para {string} e o ID do produto")
    public void oUsuárioEnviarUmaRequisicaoGETParaEOIDDoProduto(String patch) {
        try {
            ResponseEntity<ProdutoResponseDTO> response = restTemplate.getForEntity(baseUrl + patch + this.idProduto, ProdutoResponseDTO.class);

            HttpStatusCode statusCode = response.getStatusCode();
            ProdutoResponseDTO produtoResponseDTO = response.getBody();
            this.statusCode = statusCode;
            this.produtoEncontrado = produtoResponseDTO;
        } catch (HttpClientErrorException e) {
            HttpStatusCode statusCode = e.getStatusCode();
            String responseBody = e.getResponseBodyAsString();
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }
    }

    @Quando("o usuário enviar uma requisição GET para {string} com a categoria desejada")
    public void usuarioEnviaRequisicaoComDadosDoProduto(String patch) {
        try {
            ResponseEntity<List<ProdutoResponseDTO>> response = restTemplate.exchange(
                    baseUrl + patch + parametroBusca,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            HttpStatusCode statusCode = response.getStatusCode();
            List<ProdutoResponseDTO> produtoCriado = response.getBody();
            this.statusCode = statusCode;
            this.produtosEncontrados = produtoCriado;
        } catch (HttpClientErrorException e) {
            HttpStatusCode statusCode = e.getStatusCode();
            String responseBody = e.getResponseBodyAsString();
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }
    }

    @Então("o resultado da busca deve conter um produto com nome {string} com o preço {bigdecimal}")
    public void oResultadoDaBuscaDeveConterUmProdutoComNomeComOPreco(String nome, BigDecimal preco) {
        assertNotNull(produtosEncontrados);

        boolean produtoEncontrado = produtosEncontrados.stream()
                .anyMatch(produto -> produto.getNome().equals(nome) && produto.getPreco().compareTo(preco) == 0);

        assertTrue("Produto não encontrado com nome " + nome + " e preço " + preco, produtoEncontrado);
    }

    @Então("o resultado da busca não deve conter nenhum produto")
    public void oResultadoDaBuscaNaoDeveConterNenhumProduto() {
        assertNotNull(produtosEncontrados);
        assertEquals(0, produtosEncontrados.size());
    }

    @Então("o resultado da busca deve conter o produto com nome {string} com o preço {bigdecimal}")
    public void oResultadoDaBuscaDeveConterOProdutoComNomeComOPreco(String nome, BigDecimal preco) {
        assertNotNull(produtoEncontrado);
        assertEquals(nome, produtoEncontrado.getNome());
        assertEquals(preco, produtoEncontrado.getPreco());
    }

    @Então("o resultado da busca deve ser vazio")
    public void oResultadoDaBuscaDeveSerVazio() {
        assertNull(produtoEncontrado);
    }

    @Então("o resultado da busca deve ser uma lista vazia")
    public void oResultadoDaBuscaDeveSerUmaListaVazia() {
        assertNotNull(produtosEncontrados);
        assertEquals(0, produtosEncontrados.size());
    }

    @E("o status deve ser {int}")
    public void eOStatusDeveSer(int statusEsperado) {
        assertEquals(statusCode.value(), statusEsperado);
    }

    @E("conter um erro da mensagem contendo {string}")
    public void conterUmErroDaMensagemContendo(String mensagemErro) {
        assertTrue(responseBody.contains(mensagemErro));
    }


    @E("já exista um produto com nome {string} e preço {bigdecimal} e categoria {string}")
    public void jaExistUmProduceComNomePrecoECategorica(String nomeProdutoCriado, BigDecimal precoProduto, String categoriaProdutoCriado) {
        CriacaoProdutoDTO produtoRequest = criacaoProdutoDto(nomeProdutoCriado, precoProduto, categoriaProdutoCriado);
        restTemplate.postForEntity(baseUrl + "/v1/produtos", produtoRequest, ProdutoResponseDTO.class);
    }

    @E("ter dados do produto criado")
    public void terDadosDoProdutoCriado() {
        assertNotNull(produtoCriado);
        assertEquals(produtoDTO.getNome(), produtoCriado.getNome());
    }

    private CriacaoProdutoDTO criacaoProdutoDto(String nomeProdutoCriado, BigDecimal precoProduto, String categoriaProdutoCriado) {
        CriacaoProdutoDTO produtoRequest = new CriacaoProdutoDTO();
        produtoRequest.setNome(nomeProdutoCriado);
        produtoRequest.setPreco(precoProduto);
        produtoRequest.setCategoria(CategoriaProduto.valueOf(categoriaProdutoCriado));
        return produtoRequest;
    }

    private CriacaoProdutoDTO criarProduto(String atributo, String valor) {
        CriacaoProdutoDTO produtoDTO = new CriacaoProdutoDTO();
        return switch (atributo) {
            case "nome":
                produtoDTO.setNome(valor);
                yield produtoDTO;
            case "preco":
                produtoDTO.setPreco(new BigDecimal(valor));
                yield produtoDTO;
            case "categoria":
                produtoDTO.setCategoria(CategoriaProduto.valueOf(valor));
                yield produtoDTO;
            case "descricao":
                produtoDTO.setDescricao(valor);
                yield produtoDTO;
            case "imagem":
                produtoDTO.setImagem(valor);
                yield produtoDTO;
            default:
                throw new IllegalStateException("Unexpected value: " + atributo);
        };
    }

    private Object buscarAtributoProduto(String atributo, ProdutoResponseDTO produto) {
        return switch (atributo) {
            case "nome":
                yield produto.getNome();
            case "preco":
                yield produto.getPreco();
            case "categoria":
                yield produto.getCategoria();
            case "descricao":
                yield produto.getDescricao();
            case "imagem":
                yield produto.getImagem();
            default:
                throw new IllegalStateException("Unexpected value: " + atributo);
        };
    }
}
