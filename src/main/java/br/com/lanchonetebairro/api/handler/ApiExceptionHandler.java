package br.com.lanchonetebairro.api.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleGenericException(Exception e) {
        return new ResponseEntity<>(new ExceptionResponse(ErrorType.GENERIC_SERVER_ERROR,
                "Erro inesperado encontrado no servidor durante o processamento da solicitação"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected class ExceptionResponse {
        private ErrorType errorType;
        private String errorMessage;

        public ExceptionResponse(ErrorType errorType) {
            this.errorType = errorType;
        }

        public ExceptionResponse(ErrorType errorType, String errorMessage) {
            this.errorType = errorType;
            this.errorMessage = errorMessage;
        }

        public ErrorType getErrorType() {
            return errorType;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    protected enum ErrorType {
        RESOURCE_NOT_FOUND, PROCESS_FAILURE, VALIDATION_FAILURE, GENERIC_SERVER_ERROR
    }
}
