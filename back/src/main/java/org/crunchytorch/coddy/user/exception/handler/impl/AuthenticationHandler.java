package org.crunchytorch.coddy.user.exception.handler.impl;

import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.handler.IExceptionHandler;
import org.crunchytorch.coddy.user.exception.AuthenticationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationHandler implements IExceptionHandler<AuthenticationException> {

    @Override
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Response> handler(AuthenticationException e) {
        return this.handler(e, HttpStatus.BAD_REQUEST);
    }
}
