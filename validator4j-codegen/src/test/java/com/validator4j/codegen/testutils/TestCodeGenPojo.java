package com.validator4j.codegen.testutils;

import com.validator4j.core.Validatable;

@Validatable
public class TestCodeGenPojo {

    private final Integer id;

    public TestCodeGenPojo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
