package org.crunchytorch.coddy.language.api;

import org.crunchytorch.coddy.language.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/language")
public class Language {

    @Autowired
    private LanguageService languageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getLanguages() {
        return this.languageService.getLanguages();
    }
}
