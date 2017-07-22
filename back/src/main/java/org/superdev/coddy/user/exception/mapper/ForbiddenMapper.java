package org.superdev.coddy.user.exception.mapper;

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
                .entity(new org.superdev.coddy.application.data.Response(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
