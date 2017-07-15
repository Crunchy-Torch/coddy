package org.superdev.coddy.user.exception;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String msg) {
        super(msg);
    }
}
