package io.github.jenyaatnow.validator4j.apt;

import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.TypeDescriptors;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TypeMapperTest {

    private TypeMapper typeMapper;

    @BeforeEach
    void setUp() {
        typeMapper = new TypeMapper();
    }

    @ParameterizedTest
    @MethodSource("typeMappingProvider")
    void mapToValidatable(@NonNull final TypeDescriptor sourceType, @NonNull final TypeDescriptor expected) {
        final var typeMapping = typeMapper.mapToValidatable(sourceType);
        Assertions.assertEquals(expected, typeMapping.getValidatableType());
    }

    private static Stream<Arguments> typeMappingProvider() {
        return Stream.of(
            Arguments.of(TypeDescriptors.BOOLEAN, TypeDescriptors.V4J_BOOLEAN),
            Arguments.of(TypeDescriptors.INTEGER, TypeDescriptors.V4J_INTEGER),
            Arguments.of(TypeDescriptors.DOUBLE, TypeDescriptors.V4J_DOUBLE),
            Arguments.of(TypeDescriptors.STRING, TypeDescriptors.V4J_STRING),
            Arguments.of(TypeDescriptors.DATE, TypeDescriptors.V4J_DATE),
            Arguments.of(TypeDescriptors.COLLECTION_OF_VALUES, TypeDescriptors.V4J_COLLECTION_OF_VALUES),
            Arguments.of(TypeDescriptors.VALIDATABLE_TYPE, TypeDescriptors.V4J_VALIDATABLE_TYPE),
            Arguments.of(
                TypeDescriptors.COLLECTION_OF_VALIDATABLE_TYPES,
                TypeDescriptors.V4J_COLLECTION_OF_VALIDATABLE_TYPES
            )
        );
    }
}
