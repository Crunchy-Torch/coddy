package org.crunchytorch.coddy.user.exception.mapper;

import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.handler.IExceptionHandler;
import org.crunchytorch.coddy.user.exception.SecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityHandler implements IExceptionHandler<SecurityException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHandler.class);

    @Override
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Response> handler(SecurityException e) {
        LOGGER.debug(e.getMessage(), e);
        return this.handler(Response.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
