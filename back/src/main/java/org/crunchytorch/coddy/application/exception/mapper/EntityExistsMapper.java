package org.crunchytorch.coddy.application.exception.mapper;

import org.crunchytorch.coddy.application.exception.EntityExistsException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityExistsMapper implements ExceptionMapper<EntityExistsException> {
    @Override
    public Response toResponse(EntityExistsException e) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new org.crunchytorch.coddy.application.data.Response(e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
