package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestCodeGenPojo;
import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.util.resource.ResourceReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class GeneratorByFieldIntegrationTest extends AbstractCodeGeneratorIntegrationTest {

    final FieldDescriptor fieldDescriptor = new FieldDescriptor(
        "id",
        TypeDescriptors.INTEGER,
        TypeDescriptors.getUserType(TestCodeGenPojo.class)
    );

    final FieldDescriptor listFieldDescriptor = new FieldDescriptor(
        "ids",
        TypeDescriptors.INT_LIST,
        TypeDescriptors.getUserType(TestCodeGenPojo.class)
    );

    @Test
    void testGenerateGeneric() {
        final var actual = generate(listFieldDescriptor);
        final var expected = ResourceReader.instance.readResourceAsString(getExpectedGenericResource());

        Assertions.assertEquals(expected, actual);
    }

    @Override
    String generateSimple() {
        return generate(fieldDescriptor);
    }

    abstract String generate(FieldDescriptor fieldDescriptor);

    abstract TestTemplateResource getExpectedGenericResource();
}
