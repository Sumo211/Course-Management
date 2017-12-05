package com.leon.study.common;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.stream.Collectors.joining;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        APIError error = new APIError(HttpStatus.NOT_FOUND, "Resource not found", ex);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        APIError error = new APIError(HttpStatus.BAD_REQUEST, "Invalid input", ex);
        error.setDebugMessage(result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(joining(", ")));
        return new ResponseEntity<>(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST);
        error.setMessage("Invalid input");
        error.setDebugMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST, ex);
        error.setMessage("Missing param");
        return new ResponseEntity<>(error, error.getStatus());
    }

}
