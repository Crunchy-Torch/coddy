package org.superdev.coddy;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.superdev.coddy.application.api.Hello;
import org.superdev.coddy.user.api.User;
import org.superdev.coddy.user.exception.mapper.EntityExistsMapper;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Hello.class);
        register(User.class);

        //register mapper
        register(SecurityManager.class);
        register(EntityExistsMapper.class);
    }
}
