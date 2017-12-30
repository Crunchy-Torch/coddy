package org.crunchytorch.coddy.user.api;

import org.crunchytorch.coddy.application.exception.EntityExistsException;
import org.crunchytorch.coddy.application.exception.EntityNotFoundException;
import org.crunchytorch.coddy.application.utils.AppUtils;
import org.crunchytorch.coddy.user.data.in.Credential;
import org.crunchytorch.coddy.user.data.in.UpdateUser;
import org.crunchytorch.coddy.user.data.out.SimpleUser;
import org.crunchytorch.coddy.user.data.security.Permission;
import org.crunchytorch.coddy.user.data.security.Token;
import org.crunchytorch.coddy.user.filter.AuthorizationFilter;
import org.crunchytorch.coddy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the endpoint api which contains all method in order to manage the {@link org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity users}
 * All the business code is in the service part, especially in the class {@link UserService}.
 */
@Component
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    @Path("/auth")
    public Token authentication(final Credential credential) {
        return this.service.authenticate(credential);
    }

    /**
     * This method will create a {@link org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity user} from the {@link Credential credentials}.
     *
     * @param user the personnal information needed to create the associated user
     * @return the {@link SimpleUser user} created
     * @throws EntityExistsException if the given user already exists
     */
    @POST
    public SimpleUser create(UpdateUser user) {
        return this.service.create(user);
    }

    @PUT
    @Path("{" + AppUtils.API_USER_LOGIN_PATH_PARAM + "}")
    @AuthorizationFilter
    @RolesAllowed({Permission.ADMIN, Permission.PERSO_ACCOUNT})
    public SimpleUser update(@PathParam(AppUtils.API_USER_LOGIN_PATH_PARAM) final String login, final UpdateUser user) {
        user.setLogin(login);
        return this.service.update(user);
    }

    /**
     * This method will delete the {@link org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity user} from the given login.
     *
     * @param login the user's login
     * @throws EntityNotFoundException if the given login is not associated to a {@link SimpleUser user}
     */
    @DELETE
    @Path("{" + AppUtils.API_USER_LOGIN_PATH_PARAM + "}")
    @AuthorizationFilter
    @RolesAllowed({Permission.ADMIN, Permission.PERSO_ACCOUNT})
    public void delete(@PathParam(AppUtils.API_USER_LOGIN_PATH_PARAM) final String login) {
        this.service.delete(login);
    }

    @GET
    @Path("/count")
    public long count() {
        return this.service.count();
    }

    @GET
    @AuthorizationFilter
    @RolesAllowed(Permission.ADMIN)
    public List<SimpleUser> getUsers(@DefaultValue("0") @QueryParam("from") final int from,
                                     @DefaultValue("10") @QueryParam("size") final int size) {
        return this.service.getEntity(from, size).stream().map(SimpleUser::new).collect(Collectors.toList());
    }

    @GET
    @Path("/search")
    public List<SimpleUser> search(@QueryParam("login") final String loginToSearch,
                                   @DefaultValue("0") @QueryParam("from") final int from,
                                   @DefaultValue("10") @QueryParam("size") final int size) {
        return this.service.search(loginToSearch, from, size);
    }

    /**
     * @param login the user's login
     * @return the {@link SimpleUser user} associated to the given login. All critical information such as his password
     * or the salt has been deleted previously
     */
    @GET
    @Path("{" + AppUtils.API_USER_LOGIN_PATH_PARAM + "}")
    @AuthorizationFilter
    @RolesAllowed({Permission.ADMIN, Permission.PERSO_ACCOUNT})
    public SimpleUser getUserByLogin(@PathParam(AppUtils.API_USER_LOGIN_PATH_PARAM) final String login) {
        return this.service.getUserByLogin(login);
    }

    @GET
    @Path("/permission")
    public List<String> getAvailablePermissions() {
        return new ArrayList<String>() {{
            add(Permission.ADMIN);
            add(Permission.MODERATION);
            add(Permission.PERSO_ACCOUNT);
            add(Permission.PERSO_SNIPPET);
        }};
    }
}
