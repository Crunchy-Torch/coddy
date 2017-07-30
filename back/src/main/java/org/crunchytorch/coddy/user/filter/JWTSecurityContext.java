package org.crunchytorch.coddy.user.filter;

import org.crunchytorch.coddy.user.data.JWTPrincipal;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

public class JWTSecurityContext implements SecurityContext {

    private JWTPrincipal jwtPrincipal;
    private String scheme;

    public JWTSecurityContext(JWTPrincipal jwtPrincipal, String scheme) {
        this.jwtPrincipal = jwtPrincipal;
        this.scheme = scheme;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.jwtPrincipal;
    }

    /**
     * Returns a boolean indicating if the current user can access to the api method annoted by {@link javax.annotation.security.RolesAllowed}.
     *
     * @param role a <code>String</code> specifying the name of the role which is given by the annotation {@link javax.annotation.security.RolesAllowed}.
     * @return a <code>boolean</code> indicating whether the user making
     * the request belongs to a given role; <code>true</code> if the current permission user
     * match the given role;
     * <code>false</code> if it doesn't
     */
    @Override
    public boolean isUserInRole(String role) {
        boolean result = false;
        final List<String> permissions = jwtPrincipal.getPermissions();

        if (permissions != null) {
            final int length = permissions.size();
            int i = 0;

            while ((i < length) && !result) {
                result = result || permissions.get(i).equals(role);
                i++;
            }
        }
        return result;
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return "JWT";
    }
}
