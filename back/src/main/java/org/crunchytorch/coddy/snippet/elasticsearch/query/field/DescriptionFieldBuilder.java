package org.crunchytorch.coddy.snippet.elasticsearch.query.field;

import org.crunchytorch.coddy.snippet.elasticsearch.query.AbstractSnippetQueryFieldBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class DescriptionFieldBuilder extends AbstractSnippetQueryFieldBuilder {

    private static final String FIELD = "description";

    @Override
    public DescriptionFieldBuilder addWord(String word) {
        this.words.add(word);
        return this;
    }

    @Override
    public DescriptionFieldBuilder buildQuery() {
        this.words.forEach(word -> queryBuilderList.add(QueryBuilders.matchPhraseQuery(DescriptionFieldBuilder.FIELD, word)));
        return this;
    }
}
