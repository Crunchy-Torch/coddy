package org.crunchytorch.coddy.user.exception.handler.impl;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenMapper implements ExceptionMapper<ForbiddenException> {
    @Override
    public Response toResponse(ForbiddenException exception) {
        return Response.status(Response.Status.FORBIDDEN)
                .entity(new org.crunchytorch.coddy.application.data.Response(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
