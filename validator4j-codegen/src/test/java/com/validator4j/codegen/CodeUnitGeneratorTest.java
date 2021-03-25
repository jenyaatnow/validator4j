package com.validator4j.codegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CodeUnitGeneratorTest {

    @ParameterizedTest
    @MethodSource("codeGeneratorTestProvider")
    void generateVIntegerRelatedCode(final CodeUnitGenerator generator, final TestTemplateResource expectedResultResource) {
        final var getterDetails = new GetterDetails("getId", TestPojo.class);
        final var actual = generator.generate(ValidatableType.INTEGER, getterDetails);
        final var expected = ResourceReader.readResourceAsString(expectedResultResource.getRelativePath());

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> codeGeneratorTestProvider() {
        return Stream.of(
            Arguments.of(new VGetterGenerator(), TestTemplateResource.SIMPLE_VALUE_GETTER),
            Arguments.of(new VAssignmentGenerator(), TestTemplateResource.SIMPLE_ASSIGNMENT),
            Arguments.of(new VFieldGenerator(), TestTemplateResource.SIMPLE_V_FIELD)
        );
    }
}
