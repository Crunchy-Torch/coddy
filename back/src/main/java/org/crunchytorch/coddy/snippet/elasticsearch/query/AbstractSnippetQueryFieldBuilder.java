package org.crunchytorch.coddy.snippet.elasticsearch.query;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSnippetQueryFieldBuilder<T> implements SnippetQueryFieldBuilder<T> {

    protected List<String> words;

    protected List<QueryBuilder> queryBuilderList;

    protected BoolOperand operand;

    protected AbstractSnippetQueryFieldBuilder() {
        this.words = new LinkedList<>();
        this.queryBuilderList = new LinkedList<>();
    }

    public T useAndBoolOperand() {
        return this.setOperand(BoolOperand.AND);
    }

    public T useOrBoolOperand() {
        return this.setOperand(BoolOperand.OR);
    }

    @Override
    public void appendQueryBuilder(BoolQueryBuilder queryBuilder) {
        if (BoolOperand.AND.equals(this.getOperand())) {
            this.queryBuilderList.forEach(queryBuilder::must);
        } else {
            this.queryBuilderList.forEach(queryBuilder::should);
        }
    }

    @Override
    public BoolOperand getOperand() {
        return operand;
    }
}