package io.github.jenyaatnow.validator4j.sample;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenerationTestCase {

    JAVA_TYPES_POJO(
        TestTemplateResource.JAVA_TYPES_POJO_ACTUAL,
        TestTemplateResource.JAVA_TYPES_POJO_EXPECTED
    ),

    CUSTOM_TYPES_POJO(
        TestTemplateResource.CUSTOM_TYPES_POJO_ACTUAL,
        TestTemplateResource.CUSTOM_TYPES_POJO_EXPECTED
    ),

    COLLECTIONS_OF_JAVA_TYPES_POJO(
        TestTemplateResource.COLLECTIONS_OF_JAVA_TYPES_POJO_ACTUAL,
        TestTemplateResource.COLLECTIONS_OF_JAVA_TYPES_POJO_EXPECTED
    );

    private final TestTemplateResource actual;
    private final TestTemplateResource expected;
}
