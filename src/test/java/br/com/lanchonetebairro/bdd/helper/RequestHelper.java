package br.com.lanchonetebairro.bdd.helper;

import org.junit.Assert;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RequestHelper<T> {
    private final static String baseUrl = "http://localhost:8080";
    private final static RestTemplate restTemplate = new RestTemplate();

    private ResponseEntity<T> successResponse;
    private HttpClientErrorException errorResponse;

    public static <T> RequestHelper<T> realizar(String patch, HttpMethod method, Object body, Class<T> responseType) {
        return new RequestHelper<T>().executar(patch, method, body, responseType);
    }

    private RequestHelper<T> executar(String patch, HttpMethod method, Object body, Class<T> responseType) {
        try {
            this.successResponse = restTemplate.exchange(baseUrl + patch, method, new HttpEntity<>(body),
                    ParameterizedTypeReference.forType(responseType));
        } catch (HttpClientErrorException e) {
            this.errorResponse = e;
        }
        return this;
    }

    public RequestHelper<T> validaStatusEqualsTo(HttpStatus status) {
        if (errorResponse != null) {
            Assert.assertSame("Status deve ser igual", errorResponse.getStatusCode(), status);

        } else {
            Assert.assertSame("Status deve ser igual", successResponse.getStatusCode(), status);

        }
        return this;
    }

    public RequestHelper<T> validaErrorResponseContains(String text) {
        Assert.assertNotNull("Error response nao pode ser nulo", errorResponse);
        Assert.assertTrue("Error response deve conter texto", errorResponse.getResponseBodyAsString().contains(text));
        return this;
    }

    public ResponseEntity<T> getSuccessResponse() {
        return successResponse;
    }

    public HttpClientErrorException getErrorResponse() {
        return errorResponse;
    }
}