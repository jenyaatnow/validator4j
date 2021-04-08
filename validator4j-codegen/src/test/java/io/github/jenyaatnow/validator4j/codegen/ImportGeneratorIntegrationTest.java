package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.util.test.IntegrationTest;

@IntegrationTest
class ImportGeneratorIntegrationTest extends AbstractCodeGeneratorIntegrationTest {

    @Override
    String generateSimple() {
        return new ImportGenerator().generate(TypeDescriptors.V4J_INTEGER);
    }

    @Override
    TestTemplateResource getExpectedResource() {
        return TestTemplateResource.IMPORT;
    }
}
