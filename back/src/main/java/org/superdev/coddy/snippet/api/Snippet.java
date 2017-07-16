package org.superdev.coddy.snippet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.superdev.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.superdev.coddy.snippet.service.SnippetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/snippet")
public class Snippet {

    @Autowired
    private SnippetService snippetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SnippetEntity getSnippet(@PathParam("id") String id) {
        return snippetService.getSnippet(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createSnippet(@RequestBody SnippetEntity snippet) {
        snippetService.createSnippet(snippet);
    }
}
