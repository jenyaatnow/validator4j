package com.validator4j.codegen.testutils;

import com.validator4j.util.resource.ResourcePath;
import com.validator4j.codegen.TemplateResource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TestTemplateResource implements ResourcePath {

    V_CLASS("outcome/v-class", TemplateResource.V_CLASS),
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
