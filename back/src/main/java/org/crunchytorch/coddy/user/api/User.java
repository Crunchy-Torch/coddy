package org.crunchytorch.coddy.user.api;

import org.crunchytorch.coddy.application.data.ApiName;
import org.crunchytorch.coddy.application.exception.EntityExistsException;
import org.crunchytorch.coddy.application.exception.EntityNotFoundException;
import org.crunchytorch.coddy.user.data.*;
import org.crunchytorch.coddy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;
import org.crunchytorch.coddy.user.filter.AuthorizationFilter;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the endpoint api which contains all method in order to manage the {@link UserEntity users}
 * All the business code is in the service part, especially in the class {@link UserService}.
 */
@Component
@Path(ApiName.USER)
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
    public SimpleUser create(Credential credential) {
        return this.service.create(credential);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{" + ApiName.USER_LOGIN_PATH_PARAM + "}")
    @AuthorizationFilter
    @RolesAllowed({Permission.ADMIN, Permission.PERSO_ACCOUNT})
    public SimpleUser update(@PathParam(ApiName.USER_LOGIN_PATH_PARAM) final String login, final UpdateUser user) {
        user.setLogin(login);
        return this.service.update(user);
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
    @Path("{" + ApiName.USER_LOGIN_PATH_PARAM + "}")
    @AuthorizationFilter
    @RolesAllowed({Permission.ADMIN, Permission.PERSO_ACCOUNT})
    public void delete(@PathParam(ApiName.USER_LOGIN_PATH_PARAM) final String login) {
        this.service.delete(login);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @AuthorizationFilter
    @RolesAllowed(Permission.ADMIN)
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
    @Path("{" + ApiName.USER_LOGIN_PATH_PARAM + "}")
    @AuthorizationFilter
    @RolesAllowed({Permission.ADMIN, Permission.PERSO_ACCOUNT})
    public SimpleUser getUserByLogin(@PathParam(ApiName.USER_LOGIN_PATH_PARAM) final String login) {
        return this.service.getUserByLogin(login);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/permission")
    public List<String> getAvailablePermissions(){
        return new ArrayList<String>(){{
            add(Permission.ADMIN);
            add(Permission.MODERATION);
            add(Permission.PERSO_ACCOUNT);
            add(Permission.PERSO_SNIPPET);
        }};
    }
}