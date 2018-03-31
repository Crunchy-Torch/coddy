package org.crunchytorch.coddy.snippet.elasticsearch.query.field;

import org.crunchytorch.coddy.snippet.elasticsearch.query.AbstractSnippetQueryFieldBuilder;
import org.crunchytorch.coddy.snippet.elasticsearch.query.BoolOperand;
import org.elasticsearch.index.query.QueryBuilders;

public class KeywordsFieldBuilder extends AbstractSnippetQueryFieldBuilder {

    private static final String FIELD = "keywords";

    @Override
    public KeywordsFieldBuilder addWord(String word) {
        this.words.add(word);
        return this;
    }

    @Override
    public KeywordsFieldBuilder buildQuery() {
        this.words.forEach(word -> queryBuilderList.add(QueryBuilders.matchQuery(KeywordsFieldBuilder.FIELD, word)));
        return this;
    }
}
