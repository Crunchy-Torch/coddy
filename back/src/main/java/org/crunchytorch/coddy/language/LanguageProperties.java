package org.crunchytorch.coddy.language;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:language.properties")
public class LanguageProperties {

    @Value("#{'${coddy.languages}'.split(',')}")
    private List<String> languages;

    public List<String> getLanguages() {
        return languages;
    }
}
