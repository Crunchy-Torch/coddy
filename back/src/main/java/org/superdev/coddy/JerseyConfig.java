package org.superdev.coddy;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.superdev.coddy.application.api.Hello;
import org.superdev.coddy.application.exception.mapper.EntityNotFoundMapper;
import org.superdev.coddy.application.exception.mapper.ExceptMapper;
import org.superdev.coddy.user.api.User;
import org.superdev.coddy.application.exception.mapper.EntityExistsMapper;
import org.superdev.coddy.user.exception.mapper.AuthenticationMapper;
import org.superdev.coddy.user.exception.mapper.ForbiddenMapper;
import org.superdev.coddy.user.exception.mapper.NotAuthorizedMapper;
import org.superdev.coddy.user.filter.AuthorizationRequestFilter;
import org.superdev.coddy.user.filter.TokenResponseFilter;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        // register api endpoint
        register(Hello.class);
        register(User.class);

        //register mapper
        // user mapper
        register(SecurityManager.class);
        register(AuthenticationMapper.class);
        register(ForbiddenMapper.class);
        register(NotAuthorizedMapper.class);
        // generic mapper
        register(ExceptMapper.class);
        register(EntityExistsMapper.class);
        register(EntityNotFoundMapper.class);

        //register request and response filter
        register(AuthorizationRequestFilter.class);
        register(TokenResponseFilter.class);
    }
}
