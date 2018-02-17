package org.crunchytorch.coddy.snippet.service;

import org.crunchytorch.coddy.application.data.Page;
import org.crunchytorch.coddy.application.exception.EntityNotFoundException;
import org.crunchytorch.coddy.application.service.AbstractService;
import org.crunchytorch.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.crunchytorch.coddy.snippet.elasticsearch.repository.SnippetRepository;
import org.crunchytorch.coddy.user.data.security.JWTPrincipal;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.SecurityContext;
import java.util.Date;

@Service
public class SnippetService extends AbstractService<SnippetEntity> {

    @Autowired
    public SnippetService(SnippetRepository repository) {
        super(repository);
    }

    public SnippetEntity getSnippet(String id) {
        SnippetEntity entity = this.repository.findOne(id);

        if (entity == null) {
            throw new EntityNotFoundException("snippet with the id : " + id + " not found");
        }

        return entity;
    }

    public SnippetEntity create(SnippetEntity entity, SecurityContext securityContext) {
        entity.setId(null);
        // Set date to now
        Date now = new Date();
        entity.setCreated(now);
        entity.setLastModified(now);
        // Set author from token information
        entity.setAuthor(((JWTPrincipal) securityContext.getUserPrincipal()).getLogin());
        // Initiate rate
        entity.setRate(0);
        return super.create(entity);
    }

    public SnippetEntity update(SnippetEntity snippetUpdated) {
        SnippetEntity oldSnippet = this.getSnippet(snippetUpdated.getId());
        return super.create(new SnippetEntity(snippetUpdated, oldSnippet));
    }

    public Page<SnippetEntity> search(String words, int from, int size) {
        final Pageable page = new PageRequest(from, size);
        final BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.should(QueryBuilders.termQuery(SnippetEntity.getTitleField(), words));

        for (String word : words.split(" ")) {
            queryBuilder.should(QueryBuilders.matchQuery(SnippetEntity.getKeywordsField(), word));
        }

        return new Page<>(this.repository.search(queryBuilder, page));
    }

    /**
     * This method will delete the {@link SnippetEntity snippet} from the given ID.
     *
     * @param id the snippet's ID
     * @throws EntityNotFoundException if the given ID is not associated to a {@link SnippetEntity snippet}
     */
    public void delete(final String id) {
        super.delete(this.getSnippet(id));
    }
}
