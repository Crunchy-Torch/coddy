package org.crunchytorch.coddy.user.data.in;

import java.io.Serializable;

public class Credential implements Serializable {

    private static final long serialVersionUID = 3691217460687106365L;

    private String login;
    /**
     * a String type is not security safe. If you dumb the java memory, you will see clearly the password
     */
    private char[] password;

    public Credential(final String login, final char[] password) {
        this.login = login;
        this.password = password != null ? password.clone() : null;
    }

    public Credential() {
        // this blank constructor is needed by the library jackson
    }

    public String getLogin() {
        return login;
    }

    public char[] getPassword() {
        return password != null ? password.clone() : null;
    }
}
