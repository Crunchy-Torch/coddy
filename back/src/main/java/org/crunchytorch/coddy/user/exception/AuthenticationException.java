package org.crunchytorch.coddy.user.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String msg) {
        super(msg);
    }
}
