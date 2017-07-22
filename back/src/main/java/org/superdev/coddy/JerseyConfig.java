package org.superdev.coddy;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.superdev.coddy.application.api.Hello;
import org.superdev.coddy.snippet.api.Snippet;
import org.superdev.coddy.user.api.User;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Hello.class);
        register(User.class);
        register(Snippet.class);
    }
}
