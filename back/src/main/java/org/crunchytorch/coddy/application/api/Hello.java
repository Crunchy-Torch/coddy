package org.crunchytorch.coddy.application.api;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/hello")
public class Hello {

    @GET
    public String home() {

        return "Hello coddy!";
    }
}
