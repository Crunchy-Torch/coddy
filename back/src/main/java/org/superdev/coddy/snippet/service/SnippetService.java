package org.superdev.coddy.snippet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superdev.coddy.application.exception.EntityNotFoundException;
import org.superdev.coddy.application.service.AbstractService;
import org.superdev.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.superdev.coddy.snippet.elasticsearch.repository.SnippetRepository;

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

}
