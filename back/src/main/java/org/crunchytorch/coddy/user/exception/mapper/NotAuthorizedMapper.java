package org.crunchytorch.coddy.user.exception.mapper;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAuthorizedMapper implements ExceptionMapper<NotAuthorizedException> {

    @Override
    public Response toResponse(NotAuthorizedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new org.crunchytorch.coddy.application.data.Response(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
