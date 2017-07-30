package org.crunchytorch.coddy.user.filter;

import org.crunchytorch.coddy.application.data.ApiName;
import org.crunchytorch.coddy.user.data.security.JWTPrincipal;
import org.crunchytorch.coddy.user.data.security.Permission;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class JWTSecurityContext implements SecurityContext {

    private final JWTPrincipal jwtPrincipal;
    private final String scheme;
    private final MultivaluedMap<String, String> pathParameter;

    public JWTSecurityContext(final JWTPrincipal jwtPrincipal, final String scheme, final MultivaluedMap<String, String> pathParameter) {
        this.jwtPrincipal = jwtPrincipal;
        this.scheme = scheme;
        this.pathParameter = pathParameter;
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
        if (jwtPrincipal.getPermissions() == null) {
            return false;
        }

        boolean result = jwtPrincipal.getPermissions().contains(role);

        if (!result) {
            return false;
        }

        if (Permission.PERSO_ACCOUNT.equals(role)) {
            return this.pathParameter.containsKey(ApiName.USER_LOGIN_PATH_PARAM)
                    && jwtPrincipal.getLogin().equals(this.pathParameter.getFirst(ApiName.USER_LOGIN_PATH_PARAM));
        }

        return true;
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
