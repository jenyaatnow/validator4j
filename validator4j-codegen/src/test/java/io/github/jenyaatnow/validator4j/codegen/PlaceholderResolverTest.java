package io.github.jenyaatnow.validator4j.codegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.Stream;

class PlaceholderResolverTest {

    private PlaceholderResolver testable;

    @BeforeEach
    void setUp() {
        testable = new PlaceholderResolver();
    }

    /**
     * Test for {@link PlaceholderResolver#resolve(String, Stream)}.
     */
    @Test
    void resolve() {
        final var firstPlaceholder = "${placeholder.first}";
        final var secondPlaceholder = "${placeholder.second}";
        final var firstReplacement = "replacement.first";
        final var secondReplacement = "replacement.second";

        final var template = String.format("a: %s; b: %s", firstPlaceholder, secondPlaceholder);
        final var expected = String.format("a: %s; b: %s", firstReplacement, secondReplacement);

        final var resolvers = Stream.<Function<String, String>>of(
            (tmpl) -> String.format("a: %s; b: %s", firstReplacement, secondPlaceholder),
            (tmpl) -> String.format("a: %s; b: %s", firstReplacement, secondReplacement)
        );

        final var actual = testable.resolve(template, resolvers);

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test for {@link PlaceholderResolver#resolve(String, PlaceholderReplacement)}.
     */
    @Test
    void resolveMultipleMatchesOfSamePlaceholder() {
        final var placeholder = FieldTemplatePlaceholderType.FIELD_NAME.getValue();
        final var replacement = "fieldName";

        final var template = String.format("a: ${%s}; b: ${%s}", placeholder, placeholder);
        final var expected = String.format("a: %s; b: %s", replacement, replacement);

        final var placeholderReplacement =
            new PlaceholderReplacement(FieldTemplatePlaceholderType.FIELD_NAME, replacement);

        final var actual = testable.resolve(template, placeholderReplacement);

        Assertions.assertEquals(expected, actual);
    }
}
