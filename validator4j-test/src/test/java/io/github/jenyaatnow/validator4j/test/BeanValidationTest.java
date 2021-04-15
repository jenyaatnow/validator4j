package io.github.jenyaatnow.validator4j.test;

import io.github.jenyaatnow.validator4j.core.ValidationError;
import io.github.jenyaatnow.validator4j.test.beanvalidation.MainBean;
import io.github.jenyaatnow.validator4j.test.beanvalidation.NestedBean;
import io.github.jenyaatnow.validator4j.test.beanvalidation.VMainBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class BeanValidationTest {

    @Test
    void testBeanValidation() {
        final var bean = new MainBean(
            null,
            new NestedBean(
                List.of("correct", "", "correct")
            )
        );

        final var validatableBean = new VMainBean(bean);

        final var errorsReport = validatableBean.validate();

        final var expectedErrorsCount = 2;
        final var actualErrorsCount = errorsReport.getErrors().size();

        final var expectedInvalidFields = List.of(
            "id",
            "nested.strings[].<iterable element>" // TODO find out idx of invalid elements
        );
        final var actualInvalidFields = errorsReport.getErrors().stream()
            .map(ValidationError::getPath)
            .collect(Collectors.toList());

        Assertions.assertEquals(expectedErrorsCount, actualErrorsCount);
        Assertions.assertTrue(actualInvalidFields.containsAll(expectedInvalidFields));
    }
}
