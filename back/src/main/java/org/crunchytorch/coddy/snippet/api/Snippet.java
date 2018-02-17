package org.crunchytorch.coddy.snippet.api;

import org.apache.commons.lang.StringUtils;
import org.crunchytorch.coddy.application.data.Page;
import org.crunchytorch.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.crunchytorch.coddy.snippet.service.SnippetService;
import org.crunchytorch.coddy.user.data.security.Permission;
import org.crunchytorch.coddy.user.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Component
@Path("/snippet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Snippet {

    @Autowired
    private SnippetService snippetService;

    @GET
    public Page<SnippetEntity> getSnippets(@DefaultValue("0") @QueryParam("from") final int from,
                                           @DefaultValue("10") @QueryParam("size") final int size,
                                           @QueryParam("query") final String query) {
        if (StringUtils.isEmpty(query) || StringUtils.isEmpty(query.replaceAll("\\s+", ""))) {
            return snippetService.getEntity(from, size);
        }
        return snippetService.search(query, from, size);
    }

    @POST
    @AuthorizationFilter
    public SnippetEntity create(@Context SecurityContext securityContext, SnippetEntity snippet) {
        return snippetService.create(snippet, securityContext);
    }

    @GET
    @Path("{id}")
    public SnippetEntity getSnippet(@PathParam("id") String id) {
        return snippetService.getSnippet(id);
    }

    @PUT
    @Path("{id}")
    @AuthorizationFilter
    @RolesAllowed({Permission.ADMIN, Permission.PERSO_SNIPPET})
    public SnippetEntity updateSnippet(@PathParam("id") String id, SnippetEntity snippet) {
        snippet.setId(id);
        return this.snippetService.update(snippet);
    }

    @DELETE
    @Path("{id}")
    @AuthorizationFilter
    @RolesAllowed({Permission.ADMIN, Permission.PERSO_SNIPPET})
    public void delete(@PathParam("id") String id) {
        snippetService.delete(id);
    }
}
