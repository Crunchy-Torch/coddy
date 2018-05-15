package org.crunchytorch.coddy.application.exception.handler.impl;

import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.handler.IExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MediaTypeNotSupportedHandler implements IExceptionHandler<HttpMediaTypeNotSupportedException> {

    @Override
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Response> handler(HttpMediaTypeNotSupportedException e) {
        return this.handler(e.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
