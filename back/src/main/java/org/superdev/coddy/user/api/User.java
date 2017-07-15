package org.superdev.coddy.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.superdev.coddy.user.data.Credential;
import org.superdev.coddy.user.elasticsearch.entity.UserEntity;
import org.superdev.coddy.user.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/user")
public class User {

    @Autowired
    private UserService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity create(Credential credential) {
        return this.service.create(credential);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<UserEntity> getUsers(@DefaultValue("0") @QueryParam("from") final int from,
                                     @DefaultValue("10") @QueryParam("size") final int size) {
        return this.service.getUsers(from, size);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{login}")
    public UserEntity getUserByLogin(@PathParam("login") String login) {
        return this.service.getUserByLogin(login);
    }
}
