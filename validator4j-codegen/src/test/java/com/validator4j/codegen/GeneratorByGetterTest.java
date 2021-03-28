package com.validator4j.codegen;

import com.validator4j.codegen.model.TypeElementImpl;
import com.validator4j.core.ValidatableInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

class GeneratorByGetterTest {

    @ParameterizedTest
    @MethodSource("codeGeneratorTestProvider")
    void generate(final Supplier<String> generator, final TestTemplateResource expectedResource) {
        final var actual = generator.get();
        final var expected = ResourceReader.instance.readResourceAsString(expectedResource);

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> codeGeneratorTestProvider() {
        final var getterDetails = new GetterDetails(
            "getId",
            ValidatableType.INTEGER,
            TypeElementImpl.of(TestPojo.class)
        );

        return Stream.of(
            Arguments.of(
                (Supplier<String>) () -> new GetterGenerator().generate(getterDetails),
                TestTemplateResource.SIMPLE_VALUE_GETTER
            ),

            Arguments.of(
                (Supplier<String>) () -> new AssignmentGenerator().generate(getterDetails),
                TestTemplateResource.SIMPLE_ASSIGNMENT
            ),

            Arguments.of(
                (Supplier<String>) () -> new FieldGenerator().generate(getterDetails),
                TestTemplateResource.SIMPLE_V_FIELD
            ),

            // TODO move to another test class
            Arguments.of(
                (Supplier<String>) () -> new ImportGenerator().generate(TypeElementImpl.of(ValidatableInteger.class)),
                TestTemplateResource.IMPORT
            )
        );
    }
}