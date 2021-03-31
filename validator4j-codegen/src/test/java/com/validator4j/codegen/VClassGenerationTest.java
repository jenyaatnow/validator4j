package com.validator4j.codegen;

import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.ValidatableInteger;
import com.validator4j.core.ValidatableObject;
import com.validator4j.core.ValidatableReference;
import com.validator4j.util.Checks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class VClassGenerationTest {

    private final VObjectGenerator generator = new VObjectGenerator();

    @Test
    void generate() {
        final var enclosingType = TypeDescriptors.getUserType(TestPojo.class);

        final var getters = List.of(
            new GetterDescriptor("getId", TypeDescriptors.INTEGER, enclosingType),
            new GetterDescriptor("getAnotherId", TypeDescriptors.INTEGER, enclosingType)
        );

        final var imports = new HashSet<>(Set.of(
            new TypeDescriptor(ErrorsContainer.class.getName(), ValidatableType.NON_V_TYPE),
            new TypeDescriptor(ValidatableInteger.class.getName(), ValidatableType.NON_V_TYPE),
            new TypeDescriptor(ValidatableObject.class.getName(), ValidatableType.NON_V_TYPE),
            new TypeDescriptor(ValidatableReference.class.getName(), ValidatableType.NON_V_TYPE),
            new TypeDescriptor(Checks.class.getName(), ValidatableType.NON_V_TYPE),
            new TypeDescriptor(TestPojo.class.getName(), ValidatableType.USER_TYPE)
        ));

        final var sourceType = new ExtendedTypeDescriptor(
            TestPojo.class.getName(), ValidatableType.USER_TYPE, imports, getters
        );

        final var actual = generator.generate(sourceType);
        final var expected = ResourceReader.instance.readResourceAsString(TestTemplateResource.V_OBJECT);

        Assertions.assertEquals(expected, actual);
    }
}
