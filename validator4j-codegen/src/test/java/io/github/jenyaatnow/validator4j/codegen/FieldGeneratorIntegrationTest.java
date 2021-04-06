package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.util.test.IntegrationTest;
import lombok.NonNull;

@IntegrationTest
class FieldGeneratorIntegrationTest extends GeneratorByGetterIntegrationTest {

    @Override
    String generate(@NonNull final GetterDescriptor getterDescriptor) {
        return new FieldGenerator().generate(getterDescriptor);
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
