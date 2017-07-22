package org.superdev.coddy.snippet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superdev.coddy.snippet.elasticsearch.dao.SnippetDao;
import org.superdev.coddy.snippet.elasticsearch.entity.SnippetEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class SnippetService {

    @Autowired
    private SnippetDao snippetDao;

    public List<SnippetEntity> getSnippets() {
        List<SnippetEntity> results = new ArrayList<>();
        snippetDao.findAll().forEach(results::add);
        return results;
    }

    public SnippetEntity getSnippet(String id) {
        return snippetDao.find(id);
    }

    public void createSnippet(SnippetEntity snippet) {
        snippetDao.create(snippet);
    }
}
