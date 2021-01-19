package br.com.neppo.kbase.knowledgebase.domain.service.serviceException;

public class ResourceAlreadyRegisteredException extends RuntimeException {

    public ResourceAlreadyRegisteredException(String msg){
        super(msg);
    }
}
