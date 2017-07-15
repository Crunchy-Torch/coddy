package org.superdev.coddy;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.superdev.coddy.application.api.Hello;
import org.superdev.coddy.application.exception.mapper.EntityNotFoundMapper;
import org.superdev.coddy.user.api.User;
import org.superdev.coddy.application.exception.mapper.EntityExistsMapper;
import org.superdev.coddy.user.exception.mapper.AuthenticationMapper;
import org.superdev.coddy.user.exception.mapper.ForbiddenMapper;
import org.superdev.coddy.user.exception.mapper.NotAuthorizedMapper;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Hello.class);
        register(User.class);

        //register mapper
        register(SecurityManager.class);
        register(AuthenticationMapper.class);
        register(ForbiddenMapper.class);
        register(NotAuthorizedMapper.class);
        register(EntityExistsMapper.class);
        register(EntityNotFoundMapper.class);
    }
}
