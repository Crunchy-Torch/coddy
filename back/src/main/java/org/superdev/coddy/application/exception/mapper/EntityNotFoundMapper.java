package org.superdev.coddy.application.exception.mapper;

import org.superdev.coddy.application.exception.EntityNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new org.superdev.coddy.application.data.Response(e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
