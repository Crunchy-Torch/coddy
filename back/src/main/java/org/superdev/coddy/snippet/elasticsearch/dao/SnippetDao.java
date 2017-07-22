package org.superdev.coddy.snippet.elasticsearch.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superdev.coddy.application.elasticsearch.dao.IDAO;
import org.superdev.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.superdev.coddy.snippet.elasticsearch.repository.SnippetRepository;

@Service
public class SnippetDao implements IDAO<SnippetEntity> {

    @Autowired
    private SnippetRepository snippetRepository;

    @Override
    public SnippetEntity create(SnippetEntity entity) {
        return snippetRepository.save(entity);
    }

    @Override
    public SnippetEntity find(String id) {
        return snippetRepository.findOne(id);
    }

    public Iterable<SnippetEntity> findAll() {
        return snippetRepository.findAll();
    }
}
