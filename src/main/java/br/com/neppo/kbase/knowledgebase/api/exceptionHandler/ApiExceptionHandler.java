package br.com.neppo.kbase.knowledgebase.api.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<RestModelHandler>> constraintHandle(ConstraintViolationException ex){
        List<RestModelHandler> restHandler = new ArrayList<>();

        ex.getConstraintViolations().forEach( e -> {
            RestModelHandler handler = new RestModelHandler();
            handler.setMessage(e.getMessage());
            handler.setField(e.getConstraintDescriptor().toString());
            restHandler.add(handler);
        });


        return new ResponseEntity<>(restHandler, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<RestModelHandler> methodNotValidDandle(MethodArgumentNotValidException exception) {
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
        List<RestModelHandler> msgs = new ArrayList<>();

        fieldError.forEach(e -> {
            String mensagem = e.getDefaultMessage();
            RestModelHandler erro = new RestModelHandler(e.getField(), mensagem);
            msgs.add(erro);
        });

        return msgs;
    }

}
