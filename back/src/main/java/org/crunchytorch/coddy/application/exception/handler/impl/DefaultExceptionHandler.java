package org.crunchytorch.coddy.application.exception.handler.impl;

import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.handler.IExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultExceptionHandler implements IExceptionHandler<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return this.handler(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
