package org.crunchytorch.coddy.user.data.security;

import java.io.Serializable;

public class JWTToken implements Serializable {

    private static final long serialVersionUID = 3959254859062107001L;

    private String token;

    public JWTToken() {
        // this blank constructor is needed by the library jackson
    }

    public JWTToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
