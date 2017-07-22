package org.crunchytorch.coddy.application.exception;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String msg) {
        super(msg);
    }
}
