package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.util.test.IntegrationTest;
import lombok.NonNull;

@IntegrationTest
class FieldGeneratorIntegrationTest extends GeneratorByFieldIntegrationTest {

    @Override
    String generate(@NonNull final FieldDescriptor fieldDescriptor) {
        return new FieldGenerator().generate(fieldDescriptor);
    }

    @Override
    TestTemplateResource getExpectedResource() {
        return TestTemplateResource.SIMPLE_V_FIELD;
    }

    @Override
    TestTemplateResource getExpectedGenericResource() {
        return TestTemplateResource.GENERIC_V_FIELD;
    }
}
