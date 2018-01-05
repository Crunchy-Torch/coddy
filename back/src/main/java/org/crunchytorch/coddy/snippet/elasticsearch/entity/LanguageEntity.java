package org.crunchytorch.coddy.snippet.elasticsearch.entity;

import java.io.Serializable;

public class LanguageEntity implements Serializable {

    private static final long serialVersionUID = -7491682007673179557L;

    private String name;

    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
