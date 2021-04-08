package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.util.resource.ResourceReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class AbstractCodeGeneratorIntegrationTest {

    @Test
    void testGenerateSimple() {
        final var actual = generate();
        final var expected = ResourceReader.instance.readResourceAsString(getExpectedResource());

        Assertions.assertEquals(expected, actual);
    }

    abstract String generate();

    abstract TestTemplateResource getExpectedResource();
}
