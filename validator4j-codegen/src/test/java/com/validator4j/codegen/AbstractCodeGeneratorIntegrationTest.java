package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TestTemplateResource;
import com.validator4j.util.resource.ResourceReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class AbstractCodeGeneratorIntegrationTest {

    @Test
    void testGenerateSimple() {
        final var actual = generateSimple();
        final var expected = ResourceReader.instance.readResourceAsString(getExpectedResource());

        Assertions.assertEquals(expected, actual);
    }

    abstract String generateSimple();

    abstract TestTemplateResource getExpectedResource();
}
