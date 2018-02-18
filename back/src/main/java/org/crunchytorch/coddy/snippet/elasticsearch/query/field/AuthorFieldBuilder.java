package org.crunchytorch.coddy.snippet.elasticsearch.query.field;

import org.crunchytorch.coddy.snippet.elasticsearch.query.AbstractSnippetQueryFieldBuilder;
import org.crunchytorch.coddy.snippet.elasticsearch.query.BoolOperand;
import org.elasticsearch.index.query.QueryBuilders;

public class AuthorFieldBuilder extends AbstractSnippetQueryFieldBuilder<AuthorFieldBuilder> {

    private static final String FIELD = "author";

    public AuthorFieldBuilder() {
        super();
    }

    public AuthorFieldBuilder addWord(String word) {
        this.words.add(word);
        return this;
    }

    @Override
    public AuthorFieldBuilder buildQuery() {
        this.words.forEach(word -> queryBuilderList.add(QueryBuilders.termQuery(AuthorFieldBuilder.FIELD, word)));
        return this;
    }

    @Override
    public AuthorFieldBuilder setOperand(BoolOperand operand) {
        this.operand = operand;
        return this;
    }
}
