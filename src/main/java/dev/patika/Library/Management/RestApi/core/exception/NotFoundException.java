package dev.patika.Library.Management.RestApi.core.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

}
