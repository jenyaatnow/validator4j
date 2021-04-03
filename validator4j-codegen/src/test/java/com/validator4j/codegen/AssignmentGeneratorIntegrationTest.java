package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TestTemplateResource;
import com.validator4j.util.test.IntegrationTest;
import lombok.NonNull;

@IntegrationTest
class AssignmentGeneratorIntegrationTest extends GeneratorByGetterIntegrationTest {

    @Override
    String generate(@NonNull final GetterDescriptor getterDescriptor) {
        return new AssignmentGenerator().generate(getterDescriptor);
    }

    @Override
    TestTemplateResource getExpectedResource() {
        return TestTemplateResource.SIMPLE_ASSIGNMENT;
    }

    @Override
    TestTemplateResource getExpectedGenericResource() {
        return TestTemplateResource.GENERIC_ASSIGNMENT;
    }
}
