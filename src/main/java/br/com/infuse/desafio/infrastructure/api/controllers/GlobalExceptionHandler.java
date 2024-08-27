package br.com.infuse.desafio.infrastructure.api.controllers;

import br.com.infuse.desafio.domain.exceptions.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(final DomainException exception) {
        final var errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

}

@Getter
@AllArgsConstructor
class ErrorResponse {
    private String message;
}
