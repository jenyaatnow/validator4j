package com.validator4j.sample.source;

import com.validator4j.core.Validatable;

import java.util.Collection;

@Validatable
public class NestedPojo {

    private final Integer id;

    private final Collection<Integer> ids;

    public NestedPojo(Integer id, Collection<Integer> ids) {
        this.id = id;
        this.ids = ids;
    }

    public Integer getId() {
        return id;
    }

    public Collection<Integer> getIds() {
        return ids;
    }
}
