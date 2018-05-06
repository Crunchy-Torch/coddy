package org.crunchytorch.coddy.application.exception.handler;

import org.crunchytorch.coddy.application.data.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface IExceptionHandler<T extends Throwable> {

    ResponseEntity<Response> handler(T t);

    default ResponseEntity<Response> handler(T t, HttpStatus status) {
        return new ResponseEntity<>(new Response(t.getMessage()), status);
    }
}
