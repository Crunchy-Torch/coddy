package org.crunchytorch.coddy.snippet.service;

import org.crunchytorch.coddy.application.exception.EntityNotFoundException;
import org.crunchytorch.coddy.application.service.AbstractService;
import org.crunchytorch.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.crunchytorch.coddy.snippet.elasticsearch.repository.SnippetRepository;
import org.crunchytorch.coddy.user.data.security.JWTPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.SecurityContext;
import java.util.Date;

@Service
public class SnippetService extends AbstractService<SnippetEntity> {

    @Autowired
    public SnippetService(SnippetRepository repository) {
        super(repository);
    }

    public SnippetEntity getSnippet(String id) {
        SnippetEntity entity = this.repository.findOne(id);

        if (entity == null) {
            throw new EntityNotFoundException("snippet with the id : " + id + " not found");
        }

        return entity;
    }

    public SnippetEntity create(SnippetEntity entity, SecurityContext securityContext) {
        // Set date to now
        Date now = new Date();
        entity.setCreated(now);
        entity.setLastModified(now);
        // Set author from token information
        entity.setAuthor(((JWTPrincipal) securityContext.getUserPrincipal()).getLogin());
        // Initiate rate
        entity.setRate(0);
        return super.create(entity);
    }
}
