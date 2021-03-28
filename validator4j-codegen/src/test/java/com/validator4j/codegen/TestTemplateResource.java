package com.validator4j.codegen;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum TestTemplateResource implements ResourcePath {

    V_OBJECT("outcome/v-object", TemplateResource.V_OBJECT),
    IMPORT("unit/import", TemplateResource.IMPORT),
    SIMPLE_V_FIELD("unit/simple-v-field", TemplateResource.SIMPLE_V_FIELD),
    SIMPLE_ASSIGNMENT("unit/simple-assignment", TemplateResource.SIMPLE_ASSIGNMENT),
    SIMPLE_VALUE_GETTER("unit/simple-value-getter", TemplateResource.SIMPLE_VALUE_GETTER);

    private final String relativePath;
    private final TemplateResource templateResource;

    @Override
    public String getRelativePath() {
        return "expected/" + relativePath;
    }
}
