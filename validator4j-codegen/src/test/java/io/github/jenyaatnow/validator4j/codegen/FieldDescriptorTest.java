package io.github.jenyaatnow.validator4j.codegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FieldDescriptorTest {

    private static final TypeDescriptor TYPE_DESCRIPTOR = TypeDescriptors.INTEGER;

    @Test
    void testGetName() {
        final var getterDescriptor = new FieldDescriptor("getterName", TYPE_DESCRIPTOR, TYPE_DESCRIPTOR);
        Assertions.assertEquals("getGetterName", getterDescriptor.getGetterName());
    }
}
