package br.com.neppo.kbase.knowledgebase.api.exceptionHandler;

import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestModelHandler> handler(UserNotFoundException ex){
        RestModelHandler handler = new RestModelHandler();
        handler.setMessage(ex.getMessage());
        return new ResponseEntity<>(handler, HttpStatus.BAD_REQUEST);
    }
}
