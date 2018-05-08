package org.crunchytorch.coddy.security.filter;

import org.crunchytorch.coddy.security.service.JWTService;
import org.crunchytorch.coddy.user.data.security.JWTPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationRequestFilter extends GenericFilterBean {

    @Autowired
    private JWTService jwtService;

    public AuthorizationRequestFilter(JWTService service) {
        this.jwtService = service;
    }

    /**
     * Text that prefix header content (W3C convention), a.k.a. Bearer
     */
    private static final String HEADER_PREFIX = "Bearer ";

    /**
     * This method will catch any request and will analyse the header value of "Authorization" key.
     * If the key is valid, then it will extract the permission user from the token (see {@link JWTService#validateToken(String)}  validateToken()})
     * and put in a Jwt Security Context. see : {@link JWTSecurityContext}
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException            if an I/O exception occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);

        if (token == null) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "user is not authenticated");
            return;
        }

        // if not we should throw an exception
        if (token.startsWith(AuthorizationRequestFilter.HEADER_PREFIX)) {
            // Remove header prefix
            token = token.substring(AuthorizationRequestFilter.HEADER_PREFIX.length());
        }

        // if the token is valid, jwt returns an object Principal which contains the list of the user permissions
        JWTPrincipal principal = this.jwtService.validateToken(token);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthority()));
        chain.doFilter(request, response);
    }
}
