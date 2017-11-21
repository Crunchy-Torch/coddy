package org.crunchytorch.coddy.language.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Value("#{'${coddy.languages}'.split(',')}")
    private List<String> languages;

    public List<String> getLanguages() {
        return this.languages;
    }

    public boolean isLanguageValid(String language) {
        return this.languages.contains(language);
    }
}
