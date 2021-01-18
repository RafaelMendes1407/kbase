package br.com.neppo.kbase.knowledgebase.domain.service.serviceException;

public class EmailAlreadyRegisteredException extends RuntimeException{

    public EmailAlreadyRegisteredException(String msg){
        super(msg);
    }
}
