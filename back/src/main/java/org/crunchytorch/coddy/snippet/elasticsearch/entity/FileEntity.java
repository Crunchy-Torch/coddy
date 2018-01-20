package org.crunchytorch.coddy.snippet.elasticsearch.entity;

import java.io.Serializable;

public class FileEntity implements Serializable {

    private static final long serialVersionUID = -3901403840706517994L;

    private String filename;

    private LanguageEntity language;

    private String content;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
