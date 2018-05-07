package org.crunchytorch.coddy.security.filter;

import org.crunchytorch.coddy.application.utils.AppUtils;
import org.crunchytorch.coddy.snippet.service.SnippetService;
import org.crunchytorch.coddy.user.data.security.JWTPrincipal;
import org.crunchytorch.coddy.user.data.security.Permission;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class JWTSecurityContext implements SecurityContext {

    private final JWTPrincipal jwtPrincipal;
    private final String scheme;
    private final MultivaluedMap<String, String> pathParameter;
    private final SnippetService snippetService;

    public JWTSecurityContext(final JWTPrincipal jwtPrincipal, final String scheme,
                              final MultivaluedMap<String, String> pathParameter, final SnippetService snippetService) {
        this.jwtPrincipal = jwtPrincipal;
        this.scheme = scheme;
        this.pathParameter = pathParameter;
        this.snippetService = snippetService;
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
            // in that case, we need to verify that the access to the account is done by the owner and not by anyone else.
            return this.pathParameter.containsKey(AppUtils.API_USER_LOGIN_PATH_PARAM)
                    && jwtPrincipal.getLogin().equals(this.pathParameter.getFirst(AppUtils.API_USER_LOGIN_PATH_PARAM));
        }

        if (Permission.PERSO_SNIPPET.equals(role)) {
            // in that case, we need to verify that the access to the snippet is done by the owner and not by anyone else.
            return this.pathParameter.containsKey("id")
                    && jwtPrincipal.getLogin().equals(this.snippetService.getSnippet(this.pathParameter.getFirst("id")).getAuthor());
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
