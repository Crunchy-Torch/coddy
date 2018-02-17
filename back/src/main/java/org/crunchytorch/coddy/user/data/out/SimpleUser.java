package org.crunchytorch.coddy.user.data.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleUser implements Serializable {

    private static final long serialVersionUID = -1026848360977278590L;

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> permissions;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updateDate;

    public SimpleUser() {
        // this blank constructor is needed by the library jackson
    }

    public SimpleUser(UserEntity entity) {
        this.login = entity.getLogin();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.permissions = entity.getPermissions();
        this.createDate = entity.getCreateDate();
        this.updateDate = entity.getUpdateDate();
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
