package org.crunchytorch.coddy.snippet.service;

import org.crunchytorch.coddy.security.data.JWTPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This service shall be used in the annotation {@link org.springframework.security.access.prepost.PreAuthorize}.
 * It allows to check if the user that made the request can modify/delete
 * or simply access to the resource identify by a id (relative to a snippet document). The service can be used like this :
 * {@code
 *
 * @PreAuthorize("@snippetSecurityService.ownsSnippet(#id))") public void someMethodWithASecurityCheckNeeded(String id){
 * <p>
 * }
 * }
 * You can check the api of this project to see more ({@link org.crunchytorch.coddy.snippet.api.Snippet})
 */
@Service
public class SnippetSecurityService {

    private SnippetService snippetService;

    @Autowired
    public SnippetSecurityService(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    public boolean ownsSnippet(String id) {
        JWTPrincipal principal = (JWTPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal != null && principal.getLogin().equals(this.snippetService.getSnippet(id).getAuthor());
    }
}
