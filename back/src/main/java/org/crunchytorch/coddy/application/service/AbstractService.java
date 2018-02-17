package org.crunchytorch.coddy.application.service;

import org.crunchytorch.coddy.application.data.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

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

    public Page<T> getEntity(final int from, final int size) {
        Pageable page = new PageRequest(from, size);
        return new Page<>(this.repository.findAll(page));
    }
}
