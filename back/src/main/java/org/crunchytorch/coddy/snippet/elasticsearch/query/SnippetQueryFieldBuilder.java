package org.crunchytorch.coddy.snippet.elasticsearch.query;

import org.elasticsearch.index.query.BoolQueryBuilder;

public interface SnippetQueryFieldBuilder {

    SnippetQueryFieldBuilder addWord(String word);

    SnippetQueryFieldBuilder buildQuery();

    void appendQueryBuilder(BoolQueryBuilder queryBuilder);

    BoolOperand getOperand();

    SnippetQueryFieldBuilder setOperand(BoolOperand operand);

    SnippetQueryFieldBuilder useAndBoolOperand();

    SnippetQueryFieldBuilder useOrBoolOperand();
}
