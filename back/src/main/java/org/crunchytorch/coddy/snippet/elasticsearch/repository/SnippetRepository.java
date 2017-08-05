package org.crunchytorch.coddy.snippet.elasticsearch.repository;

import org.crunchytorch.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SnippetRepository extends ElasticsearchRepository<SnippetEntity, String> {

}
