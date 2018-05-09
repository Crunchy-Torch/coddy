package org.crunchytorch.coddy.security.exception;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String msg) {
        super(msg);
    }
}
