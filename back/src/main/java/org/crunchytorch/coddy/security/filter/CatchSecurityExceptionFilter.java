package org.crunchytorch.coddy.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.crunchytorch.coddy.application.data.MediaType;
import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.security.exception.ForbiddenException;
import org.crunchytorch.coddy.security.exception.NotAuthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter will only format response when a security exception
 * is thrown by the next filter : {@link AuthorizationRequestFilter}
 */
public class CatchSecurityExceptionFilter extends OncePerRequestFilter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ForbiddenException e) {
            this.injectResponse(response, HttpServletResponse.SC_FORBIDDEN, e);
        } catch (NotAuthorizedException e) {
            this.injectResponse(response, HttpServletResponse.SC_UNAUTHORIZED, e);
        }
    }

    private void injectResponse(HttpServletResponse response, int statusCode, Throwable t) throws IOException {
        Response errorResponse = new Response(t.getMessage());
        response.setStatus(statusCode);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        response.getWriter().write(MAPPER.writeValueAsString(errorResponse));
    }
}
