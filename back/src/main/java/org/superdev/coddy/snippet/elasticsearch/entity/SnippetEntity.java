package org.superdev.coddy.snippet.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(indexName = "content", type = "snippet")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SnippetEntity implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.String)
    private String title;

    @Field(type = FieldType.String)
    private String description;

    @Field( type = FieldType.Nested, index = FieldIndex.not_analyzed)
    private LanguageEntity language;

    @Field(type = FieldType.String)
    private String[] keywords;

    @Field(type = FieldType.String)
    private String content;

    @Field(type = FieldType.Nested, index = FieldIndex.no)
    private List<LinkEntity> associatedLinks;

    @Field(type = FieldType.Float, index = FieldIndex.no)
    private float rate;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String author;

    @Field(type = FieldType.Date, index = FieldIndex.no)
    private Date created;

    @Field(type = FieldType.Date, index = FieldIndex.no)
    private Date lastModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<LinkEntity> getAssociatedLinks() {
        return associatedLinks;
    }

    public void setAssociatedLinks(List<LinkEntity> associatedLinks) {
        this.associatedLinks = associatedLinks;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
