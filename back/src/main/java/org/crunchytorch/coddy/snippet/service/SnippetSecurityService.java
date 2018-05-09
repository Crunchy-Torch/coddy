package org.crunchytorch.coddy.snippet.service;

import org.crunchytorch.coddy.security.data.JWTPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
