package com.validator4j.sample.source;

import com.validator4j.core.Validatable;

@Validatable
public class GenerationSourcePojo {

    private final Integer id;

    public GenerationSourcePojo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
