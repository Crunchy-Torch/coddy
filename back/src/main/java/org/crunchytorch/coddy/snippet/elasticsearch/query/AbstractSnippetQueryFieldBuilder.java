package org.crunchytorch.coddy.snippet.elasticsearch.query;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSnippetQueryFieldBuilder implements SnippetQueryFieldBuilder {

    protected List<String> words;

    protected List<QueryBuilder> queryBuilderList;

    protected BoolOperand operand;

    protected AbstractSnippetQueryFieldBuilder() {
        this.words = new LinkedList<>();
        this.queryBuilderList = new LinkedList<>();
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
    public SnippetQueryFieldBuilder useAndBoolOperand() {
        return this.setOperand(BoolOperand.AND);
    }

    @Override
    public SnippetQueryFieldBuilder useOrBoolOperand() {
        return this.setOperand(BoolOperand.OR);
    }

    @Override
    public SnippetQueryFieldBuilder setOperand(BoolOperand operand) {
        this.operand = operand;
        return this;
    }

    @Override
    public BoolOperand getOperand() {
        return operand;
    }
}