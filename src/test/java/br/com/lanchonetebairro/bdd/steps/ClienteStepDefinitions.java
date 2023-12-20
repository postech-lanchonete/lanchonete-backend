//package br.com.lanchonetebairro.bdd.steps;
//
//import br.com.lanchonetebairro.interfaceadapters.dto.ClienteResponseDTO;
//import br.com.lanchonetebairro.interfaceadapters.dto.ProdutoResponseDTO;
//import io.cucumber.java.pt.Dado;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//public class ClienteStepDefinitions extends StatusChecker {
//    private final String baseUrl = "http://localhost:8080";
//    private final RestTemplate restTemplate = new RestTemplate();
//    ClienteResponseDTO clienteEncontrado;
//
//    @Dado("que nao exista um cliente com o nome igual a {string}, sobrenome igual a {string} e cpf igual a {string} e email {string}")
//    public void queNaoExistaUmClienteComONomeIgualASobrenomeIgualAECpfIgualAEEmail(String nome, String sobrenome, String cpf, String email) {
//        try {
//            ResponseEntity<ClienteResponseDTO> response = restTemplate.exchange(
//                    baseUrl + "/v1/clientes/",
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<>() {}
//            );
//            HttpStatusCode statusCode = response.getStatusCode();
//            ClienteResponseDTO cliente = response.getBody();
//            this.statusCode = statusCode;
//            this.clienteEncontrado = cliente;
//        } catch (HttpClientErrorException e) {
//            HttpStatusCode statusCode = e.getStatusCode();
//            String responseBody = e.getResponseBodyAsString();
//            this.statusCode = statusCode;
//            this.responseBody = responseBody;
//        }
//        assertNotEquals(nome, clienteEncontrado.getNome());
//        assertNotEquals(sobrenome, clienteEncontrado.getSobrenome());
//        assertNotEquals(email, clienteEncontrado.getEmail());
//        assertNotEquals(cpf, clienteEncontrado.getCpf());
//    }
//
//    @Dado("que exista um cliente com o nome igual a {string}, sobrenome igual a {string} e cpf igual a {string} e email {string}")
//    public void queExistaUmClienteComONomeIgualASobrenomeIgualAECpfIgualAEEmail(String nome, String sobrenome, String cpf, String email) {
//        try {
//            ResponseEntity<ClienteResponseDTO> response = restTemplate.exchange(
//                    baseUrl + "/v1/clientes/",
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<>() {}
//            );
//            HttpStatusCode statusCode = response.getStatusCode();
//            ClienteResponseDTO cliente = response.getBody();
//            this.statusCode = statusCode;
//            this.clienteEncontrado = cliente;
//        } catch (HttpClientErrorException e) {
//            HttpStatusCode statusCode = e.getStatusCode();
//            String responseBody = e.getResponseBodyAsString();
//            this.statusCode = statusCode;
//            this.responseBody = responseBody;
//        }
//        assertNotEquals(nome, clienteEncontrado.getNome());
//        assertNotEquals(sobrenome, clienteEncontrado.getSobrenome());
//        assertNotEquals(email, clienteEncontrado.getEmail());
//        assertNotEquals(cpf, clienteEncontrado.getCpf());
//    }
//}
