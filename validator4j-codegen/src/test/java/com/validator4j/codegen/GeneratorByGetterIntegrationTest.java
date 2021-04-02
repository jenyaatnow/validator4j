package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TestCodeGenPojo;
import com.validator4j.codegen.testutils.TypeDescriptors;

abstract class GeneratorByGetterIntegrationTest extends AbstractCodeGeneratorIntegrationTest {

    final GetterDescriptor getterDescriptor = new GetterDescriptor(
        "id",
        TypeDescriptors.INTEGER,
        TypeDescriptors.getUserType(TestCodeGenPojo.class)
    );
}
