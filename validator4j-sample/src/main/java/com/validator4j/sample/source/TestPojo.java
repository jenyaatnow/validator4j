package com.validator4j.sample.source;

import com.validator4j.core.Validatable;

import java.util.Collection;

@Validatable
public class TestPojo {

    private final Integer id;

    private final NestedPojo nested;

    private final Collection<Integer> articleIds;

    private final Collection<NestedPojo> pojos;

    public TestPojo(Integer id, NestedPojo nested, Collection<Integer> articleIds, Collection<NestedPojo> pojos) {
        this.id = id;
        this.nested = nested;
        this.articleIds = articleIds;
        this.pojos = pojos;
    }

    public Integer getId() {
        return id;
    }

    public NestedPojo getNested() {
        return nested;
    }

    public Collection<Integer> getArticleIds() {
        return articleIds;
    }

    public Collection<NestedPojo> getPojos() {
        return pojos;
    }
}
