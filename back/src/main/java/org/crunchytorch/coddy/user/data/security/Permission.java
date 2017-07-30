package org.crunchytorch.coddy.user.data.security;

public class Permission {

    public static final String ADMIN = "admin";

    public static final String MODERATION = "MODERATION";

    /**
     * permission to set when we need to protect a user account access.
     */
    public static final String PERSO_ACCOUNT = "personal_account";

    /**
     * permission to set when we need to protect any modifications to a private snippet.
     */
    public static final String PERSO_SNIPPET = "personal_snippet";

    private Permission(){

    }
}
