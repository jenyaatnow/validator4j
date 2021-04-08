package io.github.jenyaatnow.validator4j.codegen.testutils;

import io.github.jenyaatnow.validator4j.util.resource.ResourcePath;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TestTemplateResource implements ResourcePath {

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
