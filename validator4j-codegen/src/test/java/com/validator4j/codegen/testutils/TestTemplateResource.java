package com.validator4j.codegen.testutils;

import com.validator4j.util.resource.ResourcePath;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TestTemplateResource implements ResourcePath {

    V_CLASS("outcome/v-class"),
    IMPORT("unit/import"),
    SIMPLE_V_FIELD("unit/simple-v-field"),
    SIMPLE_ASSIGNMENT("unit/simple-assignment"),
    SIMPLE_VALUE_GETTER("unit/simple-value-getter");

    private final String relativePath;

    @Override
    public String getRelativePath() {
        return "expected/" + relativePath;
    }
}
