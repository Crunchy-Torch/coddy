package org.crunchytorch.coddy.language.service;

import org.crunchytorch.coddy.language.LanguageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageProperties languageProperties;

    public List<String> getLanguages() {
        return languageProperties.getLanguages();
    }

    public boolean isLanguageValid(String language) {
        return languageProperties.getLanguages().contains(language);
    }
}
