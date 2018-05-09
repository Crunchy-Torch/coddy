package org.crunchytorch.coddy.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.security.exception.ForbiddenException;
import org.crunchytorch.coddy.security.exception.NotAuthorizedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        response.getWriter().write(MAPPER.writeValueAsString(errorResponse));
    }
}
