package org.crunchytorch.coddy.application.exception.handler.impl;

import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.BadRequestException;
import org.crunchytorch.coddy.application.exception.handler.IExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestHandler implements IExceptionHandler<BadRequestException> {

    @Override
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handler(BadRequestException e) {
        return this.handler(e, HttpStatus.BAD_REQUEST);
    }
}
