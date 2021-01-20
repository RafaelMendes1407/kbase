package br.com.neppo.kbase.knowledgebase.domain.service.serviceException;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
