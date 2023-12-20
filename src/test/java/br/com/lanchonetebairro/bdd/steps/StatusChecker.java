//package br.com.lanchonetebairro.bdd.steps;
//
//import io.cucumber.java.pt.E;
//import io.cucumber.java.pt.Então;
//import org.springframework.http.HttpStatusCode;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public abstract class StatusChecker {
//
//    HttpStatusCode statusCode;
//    String responseBody;
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
//}
