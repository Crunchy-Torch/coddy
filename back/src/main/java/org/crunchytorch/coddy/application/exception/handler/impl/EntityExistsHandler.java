package org.crunchytorch.coddy.application.exception.handler.impl;

import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.EntityExistsException;
import org.crunchytorch.coddy.application.exception.handler.IExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EntityExistsHandler implements IExceptionHandler<EntityExistsException> {

    @Override
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Response> handler(EntityExistsException e) {
        return this.handler(e, HttpStatus.CONFLICT);
    }
}
