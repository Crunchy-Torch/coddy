package org.superdev.coddy.user.exception.mapper;

import org.superdev.coddy.user.exception.AuthenticationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AuthenticationMapper implements ExceptionMapper<AuthenticationException> {
    @Override
    public Response toResponse(AuthenticationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new org.superdev.coddy.application.data.Response(e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
