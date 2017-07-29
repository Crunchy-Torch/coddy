package org.crunchytorch.coddy;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.stereotype.Component;
import org.crunchytorch.coddy.application.exception.mapper.*;
import org.crunchytorch.coddy.user.api.User;
import org.crunchytorch.coddy.user.exception.mapper.AuthenticationMapper;
import org.crunchytorch.coddy.user.exception.mapper.NotAuthorizedMapper;
import org.crunchytorch.coddy.application.api.Hello;
import org.crunchytorch.coddy.snippet.api.Snippet;
import org.crunchytorch.coddy.user.exception.mapper.ForbiddenMapper;
import org.crunchytorch.coddy.user.filter.AuthorizationRequestFilter;
import org.crunchytorch.coddy.user.filter.TokenResponseFilter;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api/v1")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        // register api endpoint
        register(Hello.class);
        register(User.class);
        register(Snippet.class);

        //security
        register(RolesAllowedDynamicFeature.class);

        //register mapper
        // user mapper
        register(SecurityManager.class);
        register(AuthenticationMapper.class);
        register(ForbiddenMapper.class);
        register(NotAuthorizedMapper.class);

        // generic mapper
        register(ExceptMapper.class);
        register(NotFoundMapper.class);
        register(NotSupportedMapper.class);
        register(EntityExistsMapper.class);
        register(EntityNotFoundMapper.class);
        register(NotAllowedMapper.class);

        //register request and response filter
        register(AuthorizationRequestFilter.class);
        register(TokenResponseFilter.class);
    }
}
