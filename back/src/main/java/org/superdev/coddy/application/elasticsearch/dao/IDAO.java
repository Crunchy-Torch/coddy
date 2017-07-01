package org.superdev.coddy.application.elasticsearch.dao;

public interface IDAO<T> {

    T create(T entity);

    T find(String id);
}
