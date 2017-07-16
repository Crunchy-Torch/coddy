package org.superdev.coddy.snippet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superdev.coddy.snippet.elasticsearch.dao.SnippetDao;
import org.superdev.coddy.snippet.elasticsearch.entity.SnippetEntity;

@Service
public class SnippetService {

    @Autowired
    private SnippetDao snippetDao;

    public SnippetEntity getSnippet(String id) {
        return snippetDao.find(id);
    }

    public void createSnippet(SnippetEntity snippet) {
        snippetDao.create(snippet);
    }
}
