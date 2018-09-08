package org.crunchytorch.coddy.language.api;

import org.crunchytorch.coddy.application.data.MediaType;
import org.crunchytorch.coddy.language.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/language", produces = MediaType.APPLICATION_JSON)
@CrossOrigin
public class Language {

    @Autowired
    private LanguageService languageService;

    @GetMapping()
    public List<String> getLanguages() {
        return this.languageService.getLanguages();
    }
}
