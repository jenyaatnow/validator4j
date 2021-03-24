package com.validator4j.codegen;

enum TemplateResource {

    SINGLE_VALUE_GETTER("getter/single-value-getter-template.java");

    private final String relativePath;

    TemplateResource(final String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return "templates/" + relativePath;
    }
}
