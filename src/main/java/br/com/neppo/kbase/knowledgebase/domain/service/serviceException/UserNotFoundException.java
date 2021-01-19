package br.com.neppo.kbase.knowledgebase.domain.service.serviceException;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg){
        super(msg);
    }
}
