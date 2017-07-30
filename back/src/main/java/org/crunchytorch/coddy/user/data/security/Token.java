package org.crunchytorch.coddy.user.data.security;

import java.io.Serializable;

public class Token implements Serializable {

    private static final long serialVersionUID = 3959254859062107001L;

    private String token;

    public Token(){
        // this blank constructor is needed by the library jackson
    }

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
