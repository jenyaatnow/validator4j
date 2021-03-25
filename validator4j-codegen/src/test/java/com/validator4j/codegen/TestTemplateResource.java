package com.validator4j.codegen;

enum TestTemplateResource {

    SIMPLE_ASSIGNMENT("getter/simple-assignment", TemplateResource.SIMPLE_ASSIGNMENT),
    SIMPLE_VALUE_GETTER("getter/simple-value-getter", TemplateResource.SIMPLE_VALUE_GETTER);

    private final String relativePath;

    private final TemplateResource templateResource;

    TestTemplateResource(final String relativePath, final TemplateResource templateResource) {
        this.relativePath = relativePath;
        this.templateResource = templateResource;
    }

    public String getRelativePath() {
        return "expected/" + relativePath;
    }

    public TemplateResource getTemplateResource() {
        return templateResource;
    }
}
