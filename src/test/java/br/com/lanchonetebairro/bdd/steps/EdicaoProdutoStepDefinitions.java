//package br.com.lanchonetebairro.bdd.steps;
//
//import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
//import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoProdutoDTO;
//import br.com.lanchonetebairro.interfaceadapters.dto.ProdutoResponseDTO;
//import io.cucumber.java.pt.Dado;
//import io.cucumber.java.pt.E;
//import io.cucumber.java.pt.Então;
//import io.cucumber.java.pt.Quando;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import java.math.BigDecimal;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//public class EdicaoProdutoStepDefinitions extends StatusChecker {
////    private final String baseUrl = "http://localhost:8080";
////    private final RestTemplate restTemplate = new RestTemplate();
////
////    private ProdutoResponseDTO produtoCriado;
////
////    @Dado("que exista um produto com id {long} e o atributo {string} diferente a {string}")
////    public void queExistaUmProdutoComIdEOAtributoDiferenteA(Long id, String atributo, String valor) {
////        try {
////            ResponseEntity<ProdutoResponseDTO> response = restTemplate.getForEntity(baseUrl + "/v1/produtos/" + id, ProdutoResponseDTO.class);
////            ProdutoResponseDTO produtoCriado = response.getBody();
////            Object atributoProduto = buscarAtributoProduto(atributo, produtoCriado);
////            assertNotEquals(valor, atributoProduto);
////        } catch (HttpClientErrorException e) {
////            CriacaoProdutoDTO produtoDTO = new CriacaoProdutoDTO("Produto",  CategoriaProduto.BEBIDA, BigDecimal.valueOf(10),"Descrição", "Imagem");
////            restTemplate.postForEntity(baseUrl + "/v1/produtos", produtoDTO, ProdutoResponseDTO.class);
////        }
////    }
////
////    @Quando("o usuário enviar uma requisição PUT para {string} com o atributo {string} igual a {string} para o id {long}")
////    public void oUsuárioEnviarUmaRequisicaoPUTParaComOAtributoIgualAParaOId(String patch, String atributo, String valor, Long id) {
////        CriacaoProdutoDTO produtoRequest = criarProduto(atributo, valor);
////
////        try {
////            ResponseEntity<ProdutoResponseDTO> response = restTemplate.exchange(baseUrl + patch + id, HttpMethod.PUT, new HttpEntity<>(produtoRequest), ProdutoResponseDTO.class);
////
////            HttpStatusCode statusCode = response.getStatusCode();
////            ProdutoResponseDTO produtoCriado = response.getBody();
////            this.statusCode = statusCode;
////            this.produtoCriado = produtoCriado;
////        } catch (HttpClientErrorException e) {
////            this.statusCode = e.getStatusCode();
////        }
////    }
////
////    @Então("o resultado da busca deve conter um produto com atributo {string} igual a {string}")
////    public void oResultadoDaBuscaDeveConterUmProdutoComAtributoIgualA(String atributo, String valor) {
////        if(atributo.equals("preco")) {
////            BigDecimal valorInserido = (BigDecimal) buscarAtributoProduto(atributo, produtoCriado);
////            assertEquals(0, new BigDecimal(valor).compareTo(valorInserido));
////        } else {
////            assertEquals(valor, buscarAtributoProduto(atributo, produtoCriado));
////        }
////    }
////
////    private CriacaoProdutoDTO criarProduto(String atributo, String valor) {
////        CriacaoProdutoDTO produtoDTO = new CriacaoProdutoDTO();
////        return switch (atributo) {
////            case "nome":
////                produtoDTO.setNome(valor);
////                yield produtoDTO;
////            case "preco":
////                produtoDTO.setPreco(new BigDecimal(valor));
////                yield produtoDTO;
////            case "categoria":
////                produtoDTO.setCategoria(CategoriaProduto.valueOf(valor));
////                yield produtoDTO;
////            case "descricao":
////                produtoDTO.setDescricao(valor);
////                yield produtoDTO;
////            case "imagem":
////                produtoDTO.setImagem(valor);
////                yield produtoDTO;
////            default:
////                throw new IllegalStateException("Unexpected value: " + atributo);
////        };
////    }
////
////    private Object buscarAtributoProduto(String atributo, ProdutoResponseDTO produto) {
////        return switch (atributo) {
////            case "nome":
////                yield produto.getNome();
////            case "preco":
////                yield produto.getPreco();
////            case "categoria":
////                yield produto.getCategoria();
////            case "descricao":
////                yield produto.getDescricao();
////            case "imagem":
////                yield produto.getImagem();
////            default:
////                throw new IllegalStateException("Unexpected value: " + atributo);
////        };
////    }
//}
