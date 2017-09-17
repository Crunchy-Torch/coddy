package org.crunchytorch.coddy.user.data.security;

public class Permission {

    public static final String ADMIN = "admin";

    public static final String MODERATION = "moderation";

    /**
     * permission to set when we need to protect a user account access.
     */
    public static final String PERSO_ACCOUNT = "personal_account";

    /**
     * permission to set when we need to protect any modifications to a private snippet.
     */
    public static final String PERSO_SNIPPET = "personal_snippet";

    private Permission() {

    }

    /**
     * @param permission the given permission that we have to check
     * @return <code>true</code> if the given permission is recognized. <code>false</code> if not.
     */
    public static boolean isValid(final String permission) {
        return ADMIN.equals(permission) || MODERATION.equals(permission) || PERSO_ACCOUNT.equals(permission) || PERSO_SNIPPET.equals(permission);
    }
}
