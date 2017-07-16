package org.superdev.coddy.application.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new org.superdev.coddy.application.data.Response(org.superdev.coddy.application.data.Response.INTERNAL_ERROR))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
