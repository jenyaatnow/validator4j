package com.validator4j.codegen;

import com.validator4j.core.ValidatableInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

class CodeUnitGeneratorTest {

    @ParameterizedTest
    @MethodSource("codeGeneratorTestProvider")
    void generate(final Supplier<String> generator,
                  final TestTemplateResource expectedResource)
    {
        final var actual = generator.get();
        final var expected = ResourceReader.instance.readResourceAsString(expectedResource);

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> codeGeneratorTestProvider() {
        final var getterDetails = new GetterDetails("getId", TestPojo.class);

        return Stream.of(
            Arguments.of(
                (Supplier<String>) () -> new VGetterGenerator().generate(ValidatableType.INTEGER, getterDetails),
                TestTemplateResource.SIMPLE_VALUE_GETTER
            ),

            Arguments.of(
                (Supplier<String>) () -> new VAssignmentGenerator().generate(ValidatableType.INTEGER, getterDetails),
                TestTemplateResource.SIMPLE_ASSIGNMENT
            ),

            Arguments.of(
                (Supplier<String>) () -> new VFieldGenerator().generate(ValidatableType.INTEGER, getterDetails),
                TestTemplateResource.SIMPLE_V_FIELD
            ),

            Arguments.of(
                (Supplier<String>) () -> new ImportGenerator().generate(ValidatableInteger.class),
                TestTemplateResource.IMPORT
            )
        );
    }
}
