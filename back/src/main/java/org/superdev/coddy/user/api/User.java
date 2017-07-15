package org.superdev.coddy.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.superdev.coddy.user.data.Credential;
import org.superdev.coddy.user.data.Token;
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

    /**
     * return a token in json format if the user has enter correct credentials
     *
     * @param credential : user login and password in json format
     *                   <p>
     *                   {@code
     *                   {
     *                   "login": "jdoh",
     *                   "password": "U54dcS"
     *                   }
     *                   }
     *                   </p>
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/auth")
    public Token authentication(final Credential credential) {
        return this.service.authenticate(credential);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity create(Credential credential) {
        return this.service.create(credential);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{login}")
    public void delete(@PathParam("login") final String login){
        this.service.delete(login);
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
    public UserEntity getUserByLogin(@PathParam("login") final String login) {
        return this.service.getUserByLogin(login);
    }
}
