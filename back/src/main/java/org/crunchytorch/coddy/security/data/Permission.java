package org.crunchytorch.coddy.security.data;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class Permission implements GrantedAuthority {

    private static final long serialVersionUID = -4693910893773834397L;

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    private String role;

    public Permission(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

    /**
     * @param permission the given permission that we have to check
     * @return <code>true</code> if the given permission is recognized. <code>false</code> if not.
     */
    public static boolean isValid(final String permission) {
        return ADMIN.equals(permission) || USER.equals(permission);
    }

    public static List<Permission> convertStringToPerm(List<String> permissions) {
        List<Permission> perm = new ArrayList<>();
        if (permissions == null) {
            return perm;
        }

        permissions.forEach(permission -> perm.add(new Permission(permission)));
        return perm;
    }

    public static List<String> convertPermToString(List<Permission> permissions) {
        List<String> perm = new ArrayList<>();
        if (permissions == null) {
            return perm;
        }

        permissions.forEach(permission -> perm.add(permission.getAuthority()));
        return perm;
    }
}
