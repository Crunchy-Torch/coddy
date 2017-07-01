package org.superdev.coddy.user.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "account", type = "users")
public class UserEntity implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String login;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private byte[] password;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String salting;

    private String[] permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getSalting() {
        return salting;
    }

    public void setSalting(String salting) {
        this.salting = salting;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
