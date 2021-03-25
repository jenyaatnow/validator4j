package com.validator4j.codegen;

enum TemplateResource {

    SIMPLE_ASSIGNMENT("getter/simple-assignment-template"),
    SIMPLE_VALUE_GETTER("getter/simple-value-getter-template");

    private final String relativePath;

    TemplateResource(final String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return "templates/" + relativePath;
    }
}
