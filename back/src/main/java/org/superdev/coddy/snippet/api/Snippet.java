package org.superdev.coddy.snippet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.superdev.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.superdev.coddy.snippet.service.SnippetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/snippet")
public class Snippet {

    @Autowired
    private SnippetService snippetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SnippetEntity> getSnippets() {
        return snippetService.getSnippets();
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
    public void createSnippet(SnippetEntity snippet) {
        snippetService.createSnippet(snippet);
    }
}
