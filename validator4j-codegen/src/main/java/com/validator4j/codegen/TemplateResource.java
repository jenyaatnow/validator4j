package com.validator4j.codegen;

enum TemplateResource {

    SIMPLE_V_FIELD("unit/simple-v-field-template"),
    SIMPLE_ASSIGNMENT("unit/simple-assignment-template"),
    SIMPLE_VALUE_GETTER("unit/simple-value-getter-template");

    private final String relativePath;

    TemplateResource(final String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return "templates/" + relativePath;
    }
}
