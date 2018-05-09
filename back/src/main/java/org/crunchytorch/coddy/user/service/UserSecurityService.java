package org.crunchytorch.coddy.user.service;

import org.crunchytorch.coddy.security.data.JWTPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {

    public boolean ownsAccount(final String login) {
        JWTPrincipal principal = (JWTPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal != null && login.equals(principal.getLogin());
    }
}
