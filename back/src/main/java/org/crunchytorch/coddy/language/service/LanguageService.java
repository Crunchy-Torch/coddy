package org.crunchytorch.coddy.language.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LanguageService {


    private List<String> languages;

    @Autowired
    public LanguageService(@Value("${org.crunchytorch.coddy.languages:}") final String languages) {
        if(languages.isEmpty()) {
            this.languages = new ArrayList<>();
        } else {
            this.languages = Arrays.asList(languages.split(","));
        }
    }

    public List<String> getLanguages() {
        return this.languages;
    }

    public boolean isLanguageValid(String language) {
        return this.languages.contains(language);
    }
}
