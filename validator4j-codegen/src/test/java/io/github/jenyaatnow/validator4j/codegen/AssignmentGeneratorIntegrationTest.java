package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.util.test.IntegrationTest;
import lombok.NonNull;

@IntegrationTest
class AssignmentGeneratorIntegrationTest extends GeneratorByFieldIntegrationTest {

    @Override
    String generate(@NonNull final FieldDescriptor fieldDescriptor, @NonNull final TypeMappings typeMappings) {
        return new AssignmentGenerator().generate(fieldDescriptor, typeMappings);
    }

    @Override
    TestTemplateResource getExpectedResource() {
        return TestTemplateResource.SIMPLE_ASSIGNMENT;
    }
}
