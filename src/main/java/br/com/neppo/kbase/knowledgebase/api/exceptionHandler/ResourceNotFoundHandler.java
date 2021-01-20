package br.com.neppo.kbase.knowledgebase.api.exceptionHandler;

import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestModelHandler> emailHandler(ResourceNotFoundException exception){
        RestModelHandler handler = new RestModelHandler();
        handler.setMessage(exception.getMessage());
        return new ResponseEntity<>(handler, HttpStatus.BAD_REQUEST);
    }
}
