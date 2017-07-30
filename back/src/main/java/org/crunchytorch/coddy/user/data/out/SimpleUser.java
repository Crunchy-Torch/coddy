package org.crunchytorch.coddy.user.data.out;

import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;

import java.util.List;

public class SimpleUser {

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> permissions;

    public SimpleUser() {
        // this blank constructor is needed by the library jackson
    }

    public SimpleUser(UserEntity entity) {
        this.login = entity.getLogin();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.permissions = entity.getPermissions();
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
}
