package org.crunchytorch.coddy.snippet.api;

import org.crunchytorch.coddy.application.data.ApiName;
import org.crunchytorch.coddy.snippet.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.crunchytorch.coddy.snippet.elasticsearch.entity.SnippetEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path(ApiName.SNIPPET)
public class Snippet {

    @Autowired
    private SnippetService snippetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SnippetEntity> getSnippets(@DefaultValue("0") @QueryParam("from") final int from,
                                           @DefaultValue("10") @QueryParam("size") final int size) {
        return snippetService.getEntity(from, size);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public SnippetEntity getSnippet(@PathParam("id") String id) {
        return snippetService.getSnippet(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(SnippetEntity snippet) {
        snippetService.create(snippet);
    }
}
