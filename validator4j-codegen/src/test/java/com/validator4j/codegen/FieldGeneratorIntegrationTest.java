package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TestTemplateResource;
import com.validator4j.util.test.IntegrationTest;

@IntegrationTest
class FieldGeneratorIntegrationTest extends GeneratorByGetterIntegrationTest {

    @Override
    String generate() {
        return new FieldGenerator().generate(getterDescriptor);
    }

    @Override
    TestTemplateResource getExpectedResource() {
        return TestTemplateResource.SIMPLE_V_FIELD;
    }
}
