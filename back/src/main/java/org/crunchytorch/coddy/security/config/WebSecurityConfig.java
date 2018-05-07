package org.crunchytorch.coddy.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Disable CSRF (cross site request forgery)
                .csrf().disable()
                // No session will be created or used by spring security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // authorized public route
        for (Route route : this.getPublicRoute()) {
            http.authorizeRequests().antMatchers(route.getMethod(), route.getPathMatcher()).permitAll();
        }

        // Disallow everything else..
        http.authorizeRequests().anyRequest().authenticated();
    }

    private Route[] getPublicRoute() {
        return new Route[]{
                new Route("/", HttpMethod.GET),
                new Route("/language", HttpMethod.GET),
                new Route("/snippet", HttpMethod.GET),
                new Route("/snippet/{id:\\w+}", HttpMethod.GET),
                new Route("/snippet/search", HttpMethod.GET),
                new Route("/user", HttpMethod.POST),
                new Route("/user/auth", HttpMethod.POST),
                new Route("/user/permission", HttpMethod.GET)

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
