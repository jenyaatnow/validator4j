package com.validator4j.sample.source;

import lombok.Getter;

import java.util.Collection;

@Getter
public class NestedPojo {

    private final Integer id;

    private final Collection<Integer> ids;

    public NestedPojo(Integer id, Collection<Integer> ids) {
        this.id = id;
        this.ids = ids;
    }
}
