package com.validator4j.codegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VGetterGeneratorTest {

    private final VGetterGenerator getterGenerator = new VGetterGenerator();

    @Test
    void generateVIntegerGetter() {
        final var getterDetails = new GetterDetails("getId", TestPojo.class);
        final var actual = getterGenerator.generate(ValidatableType.INTEGER, getterDetails);
        final var expected = ResourceReader
            .readResourceAsString(TestTemplateResource.SIMPLE_VALUE_GETTER.getRelativePath());

        Assertions.assertEquals(expected, actual);
    }
}
