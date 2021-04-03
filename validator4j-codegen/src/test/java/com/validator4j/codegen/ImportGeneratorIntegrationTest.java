package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TestTemplateResource;
import com.validator4j.codegen.testutils.TypeDescriptors;
import com.validator4j.util.test.IntegrationTest;

@IntegrationTest
class ImportGeneratorIntegrationTest extends AbstractCodeGeneratorIntegrationTest {

    @Override
    String generateSimple() {
        return new ImportGenerator().generate(TypeDescriptors.V_INTEGER);
    }

    @Override
    TestTemplateResource getExpectedResource() {
        return TestTemplateResource.IMPORT;
    }
}
