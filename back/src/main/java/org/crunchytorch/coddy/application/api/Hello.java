package org.crunchytorch.coddy.application.api;

import org.crunchytorch.coddy.application.data.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON)
public class Hello {

    @GetMapping()
    public String home() {

        return "Hello coddy!";
    }
}
