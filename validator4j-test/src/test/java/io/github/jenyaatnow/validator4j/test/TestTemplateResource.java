package io.github.jenyaatnow.validator4j.test;

import io.github.jenyaatnow.validator4j.util.resource.ResourcePath;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TestTemplateResource implements ResourcePath {

    JAVA_TYPES_POJO_ACTUAL("generated/VJavaTypesPojo.java"),
    JAVA_TYPES_POJO_EXPECTED("expected/v-java-types-pojo"),

    CUSTOM_TYPES_POJO_ACTUAL("generated/VCustomTypesPojo.java"),
    CUSTOM_TYPES_POJO_EXPECTED("expected/v-custom-types-pojo"),

    COLLECTIONS_OF_JAVA_TYPES_POJO_ACTUAL("generated/VCollectionsOfJavaTypesPojo.java"),
    COLLECTIONS_OF_JAVA_TYPES_POJO_EXPECTED("expected/v-collections-of-java-types-pojo"),

    NESTED_POJO_COLLECTION_ACTUAL("generated/VNestedPojoCollection.java"),
    NESTED_POJO_COLLECTION_EXPECTED("expected/v-nested-pojo-collection"),

    NESTED_POJO_COLLECTION_SHOULD_BE_GENERATED_EXACTLY_ONCE_ACTUAL(
        "generated/VNestedPojoCollectionShouldBeGeneratedExactlyOnce.java"),
    NESTED_POJO_COLLECTION_SHOULD_BE_GENERATED_EXACTLY_ONCE_EXPECTED(
        "expected/v-nested-pojo-collection-should-be-generated-exactly-once"),
    ;

    @Getter
    private final String relativePath;
}
