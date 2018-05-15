package org.crunchytorch.coddy.security.exception.handler.impl;

import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.handler.IExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccessDeniedHandler implements IExceptionHandler<AccessDeniedException> {

    @Override
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> handler(AccessDeniedException e) {
        return this.handler("access denied", HttpStatus.FORBIDDEN);
    }
}
