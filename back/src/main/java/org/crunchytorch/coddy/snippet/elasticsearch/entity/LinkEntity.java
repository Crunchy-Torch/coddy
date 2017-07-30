package org.crunchytorch.coddy.snippet.elasticsearch.entity;

public class LinkEntity {

    public enum LinkType {
        DOCUMENTATION, STACK_OVERFLOW, GITHUB, OTHER
    }

    private String value;

    private LinkType type;

    private String description;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LinkType getType() {
        return type;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
