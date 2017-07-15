package org.superdev.coddy.user.data;

import java.io.Serializable;

public class Token implements Serializable {

    private static final long serialVersionUID = 3959254859062107001L;

    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
