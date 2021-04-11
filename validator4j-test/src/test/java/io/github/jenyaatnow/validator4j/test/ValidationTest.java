package io.github.jenyaatnow.validator4j.test;

import io.github.jenyaatnow.validator4j.core.ValidationRule;
import io.github.jenyaatnow.validator4j.test.source.CustomTypesPojo;
import io.github.jenyaatnow.validator4j.test.source.VValidationTargetPojo;
import io.github.jenyaatnow.validator4j.test.source.ValidationTargetPojo;
import io.github.jenyaatnow.validator4j.test.source.nested.NestedPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class ValidationTest {

    private static final String ERROR_MESSAGE = "ERROR";
    private static final String WRONG_STRING = "WRONG";
    private static final Integer WRONG_INT = Integer.MIN_VALUE;

    private static VValidationTargetPojo validatable;

    private final ValidationTargetPojo validated = new ValidationTargetPojo(
        1,
        List.of("one", WRONG_STRING, "three"),
        new NestedPojo(1),
        List.of(
            new CustomTypesPojo(new NestedPojo(1), List.of()),
            new CustomTypesPojo(new NestedPojo(1), List.of(new NestedPojo(1), new NestedPojo(WRONG_INT))),
            new CustomTypesPojo(new NestedPojo(WRONG_INT), List.of())
        )
    );

    @BeforeEach
    public void setup() {
        validatable = new VValidationTargetPojo(validated);
    }

    @ParameterizedTest
    @MethodSource("validationCaseProvider")
    public void testValidation(final Runnable runnable, final String path) {
        runnable.run();

        final var errors = validatable.validate().getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals(path, errors.iterator().next().getPath());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Stream<Arguments> validationCaseProvider() {
        final ValidationRule validationRule = (value, reject) -> reject.accept(ERROR_MESSAGE);

        return Stream.of(
            Arguments.of(
                (Runnable) () -> validatable.getInteger().validate(validationRule),
                "integer"
            ),

            Arguments.of(
                (Runnable) () -> validatable.getStrings().validateEach((str, reject) -> {
                    if (WRONG_STRING.equals(str)) reject.accept(ERROR_MESSAGE);
                }),
                "strings[1]"
            ),

            Arguments.of(
                (Runnable) () -> validatable.getNested().validate(validationRule),
                "nested"
            ),

            Arguments.of(
                (Runnable) () -> validatable.getNested().getId().validate(validationRule),
                "nested.id"
            ),

            Arguments.of(
                (Runnable) () -> validatable.getCustomTypesPojoList().validate(validationRule),
                "customTypesPojoList"
            ),

            Arguments.of(
                (Runnable) () -> validatable.getCustomTypesPojoList().validateEach((pojo, reject) -> {
                    if (WRONG_INT.equals(pojo.getNested().getId())) reject.accept(ERROR_MESSAGE);
                }),
                "customTypesPojoList[2]"
            ),

            Arguments.of(
                (Runnable) () -> validatable.getCustomTypesPojoList().forEach(vCustomTypesPojo -> {
                    vCustomTypesPojo.getNested().validate((nested, reject) -> {
                        if (WRONG_INT.equals(nested.getId())) reject.accept(ERROR_MESSAGE);
                    });
                }),
                "customTypesPojoList[2].nested"
            ),

            Arguments.of(
                (Runnable) () -> validatable.getCustomTypesPojoList().forEach(vCustomTypesPojo -> {
                    vCustomTypesPojo.getNested().getId().validate((id, reject) -> {
                        if (WRONG_INT.equals(id)) reject.accept(ERROR_MESSAGE);
                    });
                }),
                "customTypesPojoList[2].nested.id"
            ),

            Arguments.of(
                (Runnable) () -> validatable.getCustomTypesPojoList().forEach(vCustomTypesPojo -> {
                    vCustomTypesPojo.getNestedPojoList().forEach(vNested -> {
                        vNested.getId().validate((id, reject) -> {
                            if (WRONG_INT.equals(id)) reject.accept(ERROR_MESSAGE);
                        });
                    });
                }),
                "customTypesPojoList[1].nestedPojoList[1].id"
            )
        );
    }
}
