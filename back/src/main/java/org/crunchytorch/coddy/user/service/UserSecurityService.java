package org.crunchytorch.coddy.user.service;

import org.crunchytorch.coddy.security.data.JWTPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This service shall be used in the annotation {@link org.springframework.security.access.prepost.PreAuthorize}.
 * It allows to check if the user that made the request can modify/delete
 * or simply access to the resource identify by a login relative to an user account. The service can be used like this :
 * {@code
 *
 * @PreAuthorize("@userSecurityService.ownsAccount(#login))") public void someMethodWithASecurityCheckNeeded(String login){
 * <p>
 * }
 * }
 * You can check the api of this project to see more ({@link org.crunchytorch.coddy.user.api.User})
 */
@Service
public class UserSecurityService {

    public boolean ownsAccount(final String login) {
        JWTPrincipal principal = (JWTPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal != null && login.equals(principal.getLogin());
    }
}
