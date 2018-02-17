package org.crunchytorch.coddy.application.service;

import org.crunchytorch.coddy.application.data.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.io.Serializable;

public class AbstractService<T extends Serializable> {

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

    /**
     * Return a page of entity with no matching preference
     *
     * @param from : the offset from the first result you want to fetch
     * @param size : allows you to configure the maximum amount of hits to be returned
     * @return a page with the result of the research and the number of entity found.
     */
    public Page<T> getEntity(final int from, final int size) {
        Pageable page = new PageRequest(from, size);
        return new Page<>(this.repository.findAll(page));
    }
}
