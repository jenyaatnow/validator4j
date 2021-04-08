package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestCodeGenPojo;

import java.util.Set;

abstract class GeneratorByFieldIntegrationTest extends AbstractCodeGeneratorIntegrationTest {

    final FieldDescriptor fieldDescriptor = new FieldDescriptor(
        "id",
        TypeDescriptors.INTEGER,
        TypeDescriptors.getUserType(TestCodeGenPojo.class)
    );

    @Override
    String generate() {
        return generate(fieldDescriptor, new TypeMappings(Set.of(
            new TypeMapping(TypeDescriptors.INTEGER, TypeDescriptors.V4J_INTEGER)
        )));
    }

    abstract String generate(FieldDescriptor fieldDescriptor, TypeMappings typeMappings);
}
