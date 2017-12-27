package org.crunchytorch.coddy.snippet.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(indexName = "content", type = "snippet")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SnippetEntity implements Serializable {

    @Id
    private String _id;

    @Field(type = FieldType.String)
    private String title;

    @Field(type = FieldType.String)
    private String description;

    @Field(type = FieldType.Nested, index = FieldIndex.not_analyzed)
    private LanguageEntity language;

    @Field(type = FieldType.String)
    private List<String> keywords;

    @Field(type = FieldType.Nested)
    private List<FileEntity> files;

    @Field(type = FieldType.Nested, index = FieldIndex.no)
    private List<LinkEntity> associatedLinks;

    @Field(type = FieldType.Float)
    private float rate;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String author;

    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date created;

    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date lastModified;

    public SnippetEntity() {
        // this constructor must be kipped. It'll use by the json deserialization.
    }

    /**
     * @param newSnippet : The snippet which comes from the GUI. We take all information which can be updated like the title or the description.
     *                   See the oldSnippet parameter to see what is the information that cannot be updated.
     * @param oldSnippet : The snippet got from the database. It is used to extract some information that must not be modified like the unique ID,
     *                   the author who create initially the snippet or the created date.
     */
    public SnippetEntity(SnippetEntity newSnippet, SnippetEntity oldSnippet) {
        // the following data cannot be modified
        this._id = oldSnippet.getId();
        this.author = oldSnippet.getAuthor();
        this.created = oldSnippet.getCreated();

        // now the rest of the data can be updated
        this.title = newSnippet.getTitle();
        this.description = newSnippet.getDescription();
        this.language = newSnippet.getLanguage();
        this.keywords = newSnippet.getKeywords();
        this.files = newSnippet.getFiles();
        this.associatedLinks = newSnippet.getAssociatedLinks();
        this.rate = newSnippet.getRate();

        this.lastModified = new Date();
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
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
