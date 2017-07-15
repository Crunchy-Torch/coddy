package org.superdev.coddy.user.exception.mapper;

import org.superdev.coddy.user.exception.SecurityException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityMapper implements ExceptionMapper<SecurityException> {
    @Override
    public Response toResponse(SecurityException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new org.superdev.coddy.application.data.Response(org.superdev.coddy.application.data.Response.INTERNAL_ERROR))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
