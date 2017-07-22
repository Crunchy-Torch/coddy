package org.superdev.coddy.application.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.ArrayList;
import java.util.List;

public class AbstractService<T> {

    protected ElasticsearchRepository<T, String> repository;

    public AbstractService(ElasticsearchRepository<T, String> repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return this.repository.save(entity);
    }

    public void delete(T entity) {
        this.repository.delete(entity);
    }

    public List<T> getEntity(final int from, final int size) {
        Pageable page = new PageRequest(from, size);

        List<T> list = new ArrayList<>();
        this.repository.findAll(page).forEach(list::add);
        return list;
    }
}
