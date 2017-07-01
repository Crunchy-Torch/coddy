package org.superdev.coddy.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.superdev.coddy.user.data.UserEntity;
import org.superdev.coddy.user.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class User {

    @Autowired
    private UserService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{login}")
    public UserEntity getUserByLogin(@PathParam("login") String login) {
        return this.service.getUserByLogin(login);
    }
}
