package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TestTemplateResource;
import com.validator4j.util.test.IntegrationTest;

@IntegrationTest
class AssignmentGeneratorIntegrationTest extends GeneratorByGetterIntegrationTest {

    @Override
    String generate() {
        return new AssignmentGenerator().generate(getterDescriptor);
    }

    @Override
    TestTemplateResource getExpectedResource() {
        return TestTemplateResource.SIMPLE_ASSIGNMENT;
    }
}
