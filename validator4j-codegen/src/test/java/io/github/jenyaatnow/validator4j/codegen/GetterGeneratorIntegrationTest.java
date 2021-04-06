package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.util.test.IntegrationTest;
import lombok.NonNull;

@IntegrationTest
class GetterGeneratorIntegrationTest extends GeneratorByGetterIntegrationTest {

    @Override
    String generate(@NonNull final GetterDescriptor getterDescriptor) {
        return new GetterGenerator().generate(getterDescriptor);
    }

    @Override
    TestTemplateResource getExpectedResource() {
        return TestTemplateResource.SIMPLE_VALUE_GETTER;
    }

    @Override
    TestTemplateResource getExpectedGenericResource() {
        return TestTemplateResource.GENERIC_VALUE_GETTER;
    }
}
