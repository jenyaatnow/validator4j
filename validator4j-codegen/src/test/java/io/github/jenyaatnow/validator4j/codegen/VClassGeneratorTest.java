package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TestCodeGenPojo;
import io.github.jenyaatnow.validator4j.codegen.testutils.TestTemplateResource;
import io.github.jenyaatnow.validator4j.codegen.testutils.TypeDescriptors;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidatableValue;
import io.github.jenyaatnow.validator4j.core.ValidationContext;
import io.github.jenyaatnow.validator4j.util.Checks;
import io.github.jenyaatnow.validator4j.util.resource.ResourceReader;
import io.github.jenyaatnow.validator4j.util.test.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@IntegrationTest
class VClassGeneratorTest {
    private final VClassGenerator generator = new VClassGenerator();

    @Test
    void generate() {
        final var enclosingType = TypeDescriptors.getUserType(TestCodeGenPojo.class);

        final var getters = List.of(
            new FieldDescriptor("id", TypeDescriptors.INTEGER, enclosingType)
        );

        final var imports = new HashSet<>(Set.of(
            new TypeDescriptor(ValidationContext.class.getName(), DataType.OTHER),
            new TypeDescriptor(ValidatableValue.class.getName(), DataType.OTHER),
            new TypeDescriptor(ValidatableObject.class.getName(), DataType.OTHER),
            new TypeDescriptor(ValidatableReference.class.getName(), DataType.OTHER),
            new TypeDescriptor(Checks.class.getName(), DataType.OTHER)
        ));

        final var sourceType = new ExtendedTypeDescriptor(
            TestCodeGenPojo.class.getName(), DataType.VALIDATABLE, imports, getters
        );

        final var actual = generator.generate(sourceType);
        final var expected = ResourceReader.instance.readResourceAsString(TestTemplateResource.V_CLASS);

        Assertions.assertEquals(expected, actual);
    }
}
