package org.superdev.coddy.user.data;

import java.security.Principal;
import java.util.Date;
import java.util.List;

public class JWTPrincipal implements Principal, IUser {

    private final String login;
    private final String firstName;
    private final String lastName;
    private final List<String> permissions;
    private final Date beginActivationSession;

    public JWTPrincipal(
            final String login,
            final String firstName,
            final String lastName,
            final List<String> permissions,
            final Date beginActivationSession) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permissions = permissions;
        this.beginActivationSession = beginActivationSession;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public Date getBeginActivationSession() {
        return this.beginActivationSession;
    }
}
