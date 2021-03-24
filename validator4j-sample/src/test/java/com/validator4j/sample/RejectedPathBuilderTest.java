package com.validator4j.sample;

import com.validator4j.sample.generated.VTestPojo;
import com.validator4j.sample.source.NestedPojo;
import com.validator4j.sample.source.TestPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class RejectedPathBuilderTest {

    private static final String ERROR_MESSAGE = "ERROR";

    private final TestPojo testPojo = new TestPojo(
        1,
        new NestedPojo(2, List.of(1)),
        List.of(1),
        List.of(
            new NestedPojo(1, List.of(1)),
            new NestedPojo(2, List.of(1, 2, 666)),
            new NestedPojo(3, List.of(1))
        )
    );

    private static VTestPojo vTestPojo;

    @BeforeEach
    public void setup() {
        vTestPojo = new VTestPojo(testPojo);
    }

    @ParameterizedTest
    @MethodSource("validationCaseProvider")
    public void testValidation(final Runnable runnable, final String path) {
        runnable.run();

        final var errors = vTestPojo.getValidationResult().getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals(path, errors.iterator().next().getPath());
    }

    private static Stream<Arguments> validationCaseProvider() {
        final BiConsumer<Integer, Consumer<String>> validationHandler = (value, reject) -> reject.accept(ERROR_MESSAGE);

        return Stream.of(
            Arguments.of(
                (Runnable) () -> vTestPojo.getId().validate(validationHandler),
                "id"
            ),

            Arguments.of(
                (Runnable) () -> vTestPojo.getNested().getId().validate(validationHandler),
                "nested.id"
            ),

            Arguments.of(
                (Runnable) () -> vTestPojo.getArticleIds().forEach(id -> id.validate(validationHandler)),
                "articleIds[0]"
            ),

            Arguments.of(
                (Runnable) () -> vTestPojo.getNested().getIds().forEach(id -> id.validate(validationHandler)),
                "nested.ids[0]"
            ),

            Arguments.of(
                (Runnable) () -> vTestPojo.getPojos().forEach(
                    vNestedPojo -> vNestedPojo.getIds().forEach(
                        vId -> vId.validate((id, reject) -> {
                            if (id == 666) {
                                reject.accept(ERROR_MESSAGE);
                            }
                        })
                    )
                ),
                "pojos[1].ids[2]"
            )
        );
    }
}
