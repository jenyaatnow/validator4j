package io.github.jenyaatnow.validator4j.test;

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
    ),

    NESTED_POJO_COLLECTION(
        TestTemplateResource.NESTED_POJO_COLLECTION_ACTUAL,
        TestTemplateResource.NESTED_POJO_COLLECTION_EXPECTED
    ),

    NESTED_POJO_COLLECTION_SHOULD_BE_GENERATED_EXACTLY_ONCE(
        TestTemplateResource.NESTED_POJO_COLLECTION_SHOULD_BE_GENERATED_EXACTLY_ONCE_ACTUAL,
        TestTemplateResource.NESTED_POJO_COLLECTION_SHOULD_BE_GENERATED_EXACTLY_ONCE_EXPECTED
    )
    ;

    private final TestTemplateResource actual;
    private final TestTemplateResource expected;
}
