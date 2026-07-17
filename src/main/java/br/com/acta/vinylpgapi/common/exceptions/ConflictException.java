package br.com.acta.vinylpgapi.common.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String field) {
        super(field + " already exists");
    }

    public ConflictException(String fieldOne, String fieldTwo){
        super("The relation between " + fieldOne + " and " + fieldTwo + " already exists");
    }
}
