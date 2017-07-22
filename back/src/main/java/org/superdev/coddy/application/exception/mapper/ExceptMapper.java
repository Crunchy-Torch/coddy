package org.superdev.coddy.application.exception.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptMapper.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.info(e.getMessage(), e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new org.superdev.coddy.application.data.Response(org.superdev.coddy.application.data.Response.INTERNAL_ERROR))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
