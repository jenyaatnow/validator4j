package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TypeDescriptors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TypeDescriptorTest {

    private static final String fullName = "com.validator4j.test.TestClass";

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void testGetPackageName() {
        final var typeDescriptor = new TypeDescriptor(fullName, ValidatableType.NON_V_TYPE);
        Assertions.assertEquals("com.validator4j.test", typeDescriptor.getPackageName().get());
    }

    @Test
    void testGetSimpleName() {
        final var typeDescriptor = new TypeDescriptor(fullName, ValidatableType.NON_V_TYPE);
        Assertions.assertEquals("TestClass", typeDescriptor.getSimpleName());
    }

    @Test
    void testNonGenericType() {
        final var typeDescriptor = new TypeDescriptor(fullName, ValidatableType.NON_V_TYPE);
        Assertions.assertFalse(typeDescriptor.isGeneric());
    }

    @Test
    void testGenericType() {
        final var typeDescriptor =
            new TypeDescriptor(fullName, ValidatableType.NON_V_TYPE, List.of(TypeDescriptors.INTEGER));
        Assertions.assertTrue(typeDescriptor.isGeneric());
    }
}
