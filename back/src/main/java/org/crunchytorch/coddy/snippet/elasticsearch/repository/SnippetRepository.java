package org.crunchytorch.coddy.snippet.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.crunchytorch.coddy.snippet.elasticsearch.entity.SnippetEntity;

public interface SnippetRepository extends ElasticsearchRepository<SnippetEntity, String> {

}
