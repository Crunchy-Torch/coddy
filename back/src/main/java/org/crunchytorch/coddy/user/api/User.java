package org.crunchytorch.coddy.user.api;

import org.crunchytorch.coddy.application.exception.EntityExistsException;
import org.crunchytorch.coddy.application.exception.EntityNotFoundException;
import org.crunchytorch.coddy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.crunchytorch.coddy.user.data.Credential;
import org.crunchytorch.coddy.user.data.Token;
import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;
import org.crunchytorch.coddy.user.filter.AuthorizationFilter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * This class is the endpoint api which contains all method in order to manage the {@link UserEntity users}
 * All the business code is in the service part, especially in the class {@link UserService}.
 */
@Component
@Path("/user")
public class User {

    @Autowired
    private UserService service;

    /**
     * return a token in json format if the user has entered correct credentials
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

    /**
     * This method will create a {@link UserEntity user} from the {@link Credential credentials}.
     *
     * @param credential the personnal information needed to create the associated user
     * @return the {@link UserEntity user} created
     * @throws EntityExistsException if the given user already exists
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity create(Credential credential) {
        return this.service.create(credential);
    }

    /**
     * This method will delete the {@link UserEntity user} from the given login.
     *
     * @param login the user's login
     * @throws EntityNotFoundException if the given login is not associated to a {@link UserEntity user}
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{login}")
    @AuthorizationFilter
    public void delete(@PathParam("login") final String login) {
        this.service.delete(login);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @AuthorizationFilter
    public List<UserEntity> getUsers(@DefaultValue("0") @QueryParam("from") final int from,
                                     @DefaultValue("10") @QueryParam("size") final int size) {
        return this.service.getEntity(from, size);
    }

    /**
     * @param login the user's login
     * @return the {@link UserEntity user} associated to the given login. All critical information such as his password
     * or the salt has been deleted previously
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{login}")
    @AuthorizationFilter
    public UserEntity getUserByLogin(@PathParam("login") final String login) {
        return this.service.getUserByLogin(login);
    }
}
