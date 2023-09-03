package br.com.lanchonetebairro.applicationrules.exceptions;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
