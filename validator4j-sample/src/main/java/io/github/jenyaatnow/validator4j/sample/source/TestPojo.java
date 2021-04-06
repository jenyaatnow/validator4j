package io.github.jenyaatnow.validator4j.sample.source;

import lombok.Getter;

import java.util.Collection;

@Getter
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
}
