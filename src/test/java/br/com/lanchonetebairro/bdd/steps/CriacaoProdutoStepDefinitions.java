//package br.com.lanchonetebairro.bdd.steps;
//
//import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
//import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoProdutoDTO;
//import br.com.lanchonetebairro.interfaceadapters.dto.ProdutoResponseDTO;
//import br.com.lanchonetebairro.interfaceadapters.gateways.ProdutoGateway;
//import io.cucumber.java.After;
//import io.cucumber.java.pt.Dado;
//import io.cucumber.java.pt.E;
//import io.cucumber.java.pt.Entao;
//import io.cucumber.java.pt.Então;
//import io.cucumber.java.pt.Quando;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import java.math.BigDecimal;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class CriacaoProdutoStepDefinitions extends StatusChecker {
//
//    private final String baseUrl = "http://localhost:8080";
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    private ProdutoResponseDTO produtoCriado;
//    private CriacaoProdutoDTO produtoDTO;
//
//    @Dado("que um usuário queira cadastrar um produto com nome {string} e preço {bigdecimal} e categoria {string}")
//    public void usuarioQuerCadastrarProdutoComNomeEPreco(String nomeProduto, BigDecimal precoProduto, String categoriaProduto) {
//        this.produtoDTO = criacaoProdutoDto(nomeProduto, precoProduto, categoriaProduto);
//    }
//
//    @Quando("o usuário enviar uma requisição POST para {string} com os dados do produto")
//    public void usuarioEnviaRequisicaoComDadosDoProduto(String patch) {
//        try {
//            ResponseEntity<ProdutoResponseDTO> response = restTemplate.postForEntity(baseUrl + patch, this.produtoDTO, ProdutoResponseDTO.class);
//
//            HttpStatusCode statusCode = response.getStatusCode();
//            ProdutoResponseDTO produtoCriado = response.getBody();
//            this.statusCode = statusCode;
//            this.produtoCriado = produtoCriado;
//        } catch (HttpClientErrorException e) {
//            HttpStatusCode statusCode = e.getStatusCode();
//            String responseBody = e.getResponseBodyAsString();
//            this.statusCode = statusCode;
//            this.responseBody = responseBody;
//        }
//    }
//
//    @E("já exista um produto com nome {string} e preço {bigdecimal} e categoria {string}")
//    public void jaExistUmProduceComNomePrecoECategorica(String nomeProdutoCriado, BigDecimal precoProduto, String categoriaProdutoCriado) {
//        CriacaoProdutoDTO produtoRequest = criacaoProdutoDto(nomeProdutoCriado, precoProduto, categoriaProdutoCriado);
//        restTemplate.postForEntity(baseUrl + "/v1/produtos", produtoRequest, ProdutoResponseDTO.class);
//    }
//
//    @E("ter dados do produto criado")
//    public void terDadosDoProdutoCriado() {
//        assertNotNull(produtoCriado);
//        assertEquals(produtoDTO.getNome(), produtoCriado.getNome());
//    }
//
//    private CriacaoProdutoDTO criacaoProdutoDto(String nomeProdutoCriado, BigDecimal precoProduto, String categoriaProdutoCriado) {
//        CriacaoProdutoDTO produtoRequest = new CriacaoProdutoDTO();
//        produtoRequest.setNome(nomeProdutoCriado);
//        produtoRequest.setPreco(precoProduto);
//        produtoRequest.setCategoria(CategoriaProduto.valueOf(categoriaProdutoCriado));
//        return produtoRequest;
//    }
//}
