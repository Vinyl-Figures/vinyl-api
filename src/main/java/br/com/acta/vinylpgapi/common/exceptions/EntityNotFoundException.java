package br.com.acta.vinylpgapi.common.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String entityName, Long id){
        super("The " + entityName + " with ID " + id + " was not found");
    }
}
