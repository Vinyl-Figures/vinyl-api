package br.com.acta.vinylpgapi.common.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("The requested resource is not owned by the specified user.");
    }
}
