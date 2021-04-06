package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestCodeGenPojo;
import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.codegen.testutils.TypeDescriptors;
import io.github.jenyaatnow.validator4j.util.resource.ResourceReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class GeneratorByGetterIntegrationTest extends AbstractCodeGeneratorIntegrationTest {

    final GetterDescriptor getterDescriptor = new GetterDescriptor(
        "id",
        TypeDescriptors.INTEGER,
        TypeDescriptors.getUserType(TestCodeGenPojo.class)
    );

    final GetterDescriptor listGetterDescriptor = new GetterDescriptor(
        "ids",
        TypeDescriptors.INT_LIST,
        TypeDescriptors.getUserType(TestCodeGenPojo.class)
    );

    @Test
    void testGenerateGeneric() {
        final var actual = generate(listGetterDescriptor);
        final var expected = ResourceReader.instance.readResourceAsString(getExpectedGenericResource());

        Assertions.assertEquals(expected, actual);
    }

    @Override
    String generateSimple() {
        return generate(getterDescriptor);
    }

    abstract String generate(GetterDescriptor getterDescriptor);

    abstract TestTemplateResource getExpectedGenericResource();
}
