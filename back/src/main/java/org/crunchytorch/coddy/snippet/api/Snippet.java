package org.crunchytorch.coddy.snippet.api;

import org.apache.commons.lang.StringUtils;
import org.crunchytorch.coddy.application.data.MediaType;
import org.crunchytorch.coddy.application.data.Page;
import org.crunchytorch.coddy.security.data.Permission;
import org.crunchytorch.coddy.snippet.data.SearchBody;
import org.crunchytorch.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.crunchytorch.coddy.snippet.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/snippet", produces = MediaType.APPLICATION_JSON)
@CrossOrigin
public class Snippet {

    @Autowired
    private SnippetService snippetService;

    @GetMapping()
    public Page<SnippetEntity> getSnippets(@RequestParam(value = "from", defaultValue = "0") final int from,
                                           @RequestParam(value = "size", defaultValue = "10") final int size,
                                           @RequestParam(value = "query", required = false) final String query) {
        if (StringUtils.isEmpty(query) || StringUtils.isEmpty(query.replaceAll("\\s+", ""))) {
            return snippetService.getEntity(from, size);
        }
        return snippetService.search(query, from, size);
    }

    @GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON)
    public Page<SnippetEntity> search(@RequestParam(value = "from", defaultValue = "0") final int from,
                                      @RequestParam(value = "size", defaultValue = "10") final int size,
                                      @RequestBody SearchBody searchBody) {
        return snippetService.search(searchBody, from, size);
    }

    @PostMapping()
    public SnippetEntity create(@RequestBody SnippetEntity snippet) {
        return snippetService.createSnippet(snippet);
    }

    @GetMapping(path = "/{id}")
    public SnippetEntity getSnippet(@PathVariable("id") String id) {
        return snippetService.getSnippet(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('" + Permission.ADMIN + "') or (hasRole('" + Permission.USER + "') and @snippetSecurityService.ownsSnippet(#id))")
    public SnippetEntity updateSnippet(@PathVariable("id") String id, @RequestBody SnippetEntity snippet) {
        snippet.setId(id);
        return this.snippetService.update(snippet);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('" + Permission.ADMIN + "') or (hasRole('" + Permission.USER + "') and @snippetSecurityService.ownsSnippet(#id))")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        snippetService.delete(id);
    }
}
