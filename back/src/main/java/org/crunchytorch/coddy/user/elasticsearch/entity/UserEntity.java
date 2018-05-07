package org.crunchytorch.coddy.user.elasticsearch.entity;

import org.crunchytorch.coddy.user.data.IUser;
import org.crunchytorch.coddy.user.data.in.UpdateUser;
import org.crunchytorch.coddy.security.utils.SecurityUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(indexName = "account", type = "user")
public class UserEntity implements Serializable, IUser {

    @Id
    private String _id;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String login;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private byte[] password;

    @Field(type = FieldType.String, index = FieldIndex.no)
    private String firstName;

    @Field(type = FieldType.String, index = FieldIndex.no)
    private String lastName;

    @Field(type = FieldType.String, index = FieldIndex.no)
    private String email;

    @Field(type = FieldType.String, index = FieldIndex.no)
    private byte[] salt;

    @Field(type = FieldType.String)
    private List<String> permissions;

    @Field(type = FieldType.Date)
    private Date createDate;

    @Field(type = FieldType.Date)
    private Date updateDate;

    public UserEntity() {
    }

    public UserEntity(UpdateUser user, List<String> permissions) {
        this.login = user.getLogin();
        this.generatePasswordAndSalt(user.getPassword());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.permissions = permissions;
        this.createDate = this.updateDate = new Date();
    }

    public UserEntity(UpdateUser user, UserEntity oldEntity) {
        // the following data cannot be modified
        this._id = oldEntity.getId();
        this.login = oldEntity.getLogin();
        this.permissions = oldEntity.getPermissions();
        this.createDate = oldEntity.getCreateDate();

        // now the rest of the data can be updated
        if (user.getPassword() != null) {
            this.generatePasswordAndSalt(user.getPassword());
        } else {
            this.password = oldEntity.getPassword();
            this.salt = oldEntity.getSalt();
        }

        this.updateDate = new Date();

        this.firstName = user.getFirstName() != null ? user.getFirstName() : oldEntity.getFirstName();
        this.lastName = user.getLastName() != null ? user.getLastName() : oldEntity.getLastName();
        this.email = user.getEmail() != null ? user.getEmail() : oldEntity.getEmail();
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password != null ? password.clone() : null;
    }

    public void setPassword(byte[] password) {
        this.password = password != null ? password.clone() : null;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSalt() {
        return salt != null ? salt.clone() : null;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt != null ? salt.clone() : null;
    }

    @Override
    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    private void generatePasswordAndSalt(char[] password) {
        this.salt = SecurityUtils.generateSalt();
        this.password = SecurityUtils.hash(password, this.salt);
    }
}
