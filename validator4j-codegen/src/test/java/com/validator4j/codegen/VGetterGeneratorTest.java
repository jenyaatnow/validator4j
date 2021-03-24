package com.validator4j.codegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VGetterGeneratorTest {

    private final VGetterGenerator getterGenerator = new VGetterGenerator();

    @Test
    void generateVIntegerGetter() {
        final var actual = getterGenerator.generateVIntegerGetter("id");
        final var expected = ResourceReader
            .readResourceAsString(TestTemplateResource.SINGLE_VALUE_GETTER.getRelativePath());

        Assertions.assertEquals(expected, actual);
    }
}
