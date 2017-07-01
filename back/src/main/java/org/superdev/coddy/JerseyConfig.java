package org.superdev.coddy;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.superdev.coddy.application.api.Hello;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Hello.class);
    }
}
