package com.validator4j.codegen;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum TemplateResource implements ResourcePath {

    V_OBJECT("outcome/v-object-template"),
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
