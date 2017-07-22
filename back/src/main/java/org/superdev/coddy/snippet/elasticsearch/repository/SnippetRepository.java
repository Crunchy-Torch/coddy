package org.superdev.coddy.snippet.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.superdev.coddy.snippet.elasticsearch.entity.SnippetEntity;

public interface SnippetRepository extends ElasticsearchRepository<SnippetEntity, String> {

}
