package com.validator4j.codegen;

enum TestTemplateResource implements ResourcePath {

    IMPORT("unit/import", TemplateResource.IMPORT),
    SIMPLE_V_FIELD("unit/simple-v-field", TemplateResource.SIMPLE_V_FIELD),
    SIMPLE_ASSIGNMENT("unit/simple-assignment", TemplateResource.SIMPLE_ASSIGNMENT),
    SIMPLE_VALUE_GETTER("unit/simple-value-getter", TemplateResource.SIMPLE_VALUE_GETTER);

    private final String relativePath;

    private final TemplateResource templateResource;

    TestTemplateResource(final String relativePath, final TemplateResource templateResource) {
        this.relativePath = relativePath;
        this.templateResource = templateResource;
    }

    @Override
    public String getRelativePath() {
        return "expected/" + relativePath;
    }

    public TemplateResource getTemplateResource() {
        return templateResource;
    }
}
