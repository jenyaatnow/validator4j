package com.validator4j.codegen;

enum TemplateResource implements ResourcePath {

    IMPORT("unit/import-template"),
    SIMPLE_V_FIELD("unit/simple-v-field-template"),
    SIMPLE_ASSIGNMENT("unit/simple-assignment-template"),
    SIMPLE_VALUE_GETTER("unit/simple-value-getter-template");

    private final String relativePath;

    TemplateResource(final String relativePath) {
        this.relativePath = relativePath;
    }

    @Override
    public String getRelativePath() {
        return "templates/" + relativePath;
    }
}
