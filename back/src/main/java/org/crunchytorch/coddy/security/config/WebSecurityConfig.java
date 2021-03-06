package org.crunchytorch.coddy.security.config;

import org.crunchytorch.coddy.security.filter.AuthorizationRequestFilter;
import org.crunchytorch.coddy.security.filter.CatchSecurityExceptionFilter;
import org.crunchytorch.coddy.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTService service;

    @Autowired
    public WebSecurityConfig(JWTService service) {
        super();
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                // Disable CSRF (cross site request forgery) We don't need it since we don't use any cookie
                .csrf().disable()
                // No session will be created or used by spring security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest().authenticated().and()

                // configure filter
                .addFilterBefore(new CatchSecurityExceptionFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterBefore(new AuthorizationRequestFilter(service), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) {
        for (Route route : this.getPublicRoute()) {
            // the public route will be completely ignored by the previous rules configured in WebSecurityConfig#configure method
            web.ignoring().antMatchers(route.getMethod(), route.getPathMatcher());
        }
    }

    private Route[] getPublicRoute() {
        return new Route[]{
                new Route("/", HttpMethod.GET),
                new Route("/language", HttpMethod.GET),
                new Route("/snippet", HttpMethod.GET),
                new Route("/snippet/{id:\\w+}", HttpMethod.GET),
                new Route("/snippet/search", HttpMethod.GET),
                new Route("/user/auth", HttpMethod.POST),
                new Route("/user", HttpMethod.POST)
        };
    }

    private static class Route {
        private String pathMatcher;
        private HttpMethod method;

        Route(String pathMatcher, HttpMethod method) {
            this.method = method;
            this.pathMatcher = pathMatcher;
        }

        String getPathMatcher() {
            return pathMatcher;
        }

        HttpMethod getMethod() {
            return method;
        }
    }
}
