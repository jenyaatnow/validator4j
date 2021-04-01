package com.validator4j.codegen;

import com.validator4j.util.resource.ResourcePath;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TemplateResource implements ResourcePath {

    V_CLASS("outcome/v-class-template"),
    V_COLLECTION("outcome/v-collection-template"),
    IMPORT("unit/import-template"),
    SIMPLE_V_FIELD("unit/simple-v-field-template"),
    SIMPLE_ASSIGNMENT("unit/simple-assignment-template"),
    SIMPLE_VALUE_GETTER("unit/simple-value-getter-template");

    private final String relativePath;

    @Override
    public String getRelativePath() {
        return "templates/" + relativePath;
    }
}
