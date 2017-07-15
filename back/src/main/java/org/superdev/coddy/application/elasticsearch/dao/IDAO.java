package org.superdev.coddy.application.elasticsearch.dao;

import java.util.List;

public interface IDAO<T> {

    T create(T entity);

    void delete(T entity);

    T find(String id);

    List<T> findAll(final int from, final int size);
}
