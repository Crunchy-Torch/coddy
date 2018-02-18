package org.crunchytorch.coddy.snippet.elasticsearch.query;

import org.elasticsearch.index.query.BoolQueryBuilder;

public interface SnippetQueryFieldBuilder<T> {

    T addWord(String word);

    T buildQuery();

    void appendQueryBuilder(BoolQueryBuilder queryBuilder);

    BoolOperand getOperand();

    T setOperand(BoolOperand operand);
}
