package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TypeDescriptors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GetterDescriptorTest {

    private static final TypeDescriptor TYPE_DESCRIPTOR = TypeDescriptors.INTEGER;

    @Test
    void testGetName() {
        final var getterDescriptor = new GetterDescriptor("getterName", TYPE_DESCRIPTOR, TYPE_DESCRIPTOR);
        Assertions.assertEquals("getGetterName", getterDescriptor.getName());
    }
}
