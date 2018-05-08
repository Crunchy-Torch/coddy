package org.crunchytorch.coddy.security.config;

import org.crunchytorch.coddy.security.filter.AuthorizationRequestFilter;
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

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
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
                // Disable CSRF (cross site request forgery)
                .csrf().disable()
                // No session will be created or used by spring security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //.antMatchers("/user/search").hasAuthority("admin")
                .anyRequest().authenticated();

        AuthorizationRequestFilter requestFilter = new AuthorizationRequestFilter(service);
        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) {
        for (Route route : this.getPublicRoute()) {
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