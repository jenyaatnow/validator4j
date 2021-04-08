package io.github.jenyaatnow.validator4j.sample.generated;

import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidatableValue;
import io.github.jenyaatnow.validator4j.core.ValidationContext;
import io.github.jenyaatnow.validator4j.sample.source.TestPojo;
import io.github.jenyaatnow.validator4j.util.Checks;

@SuppressWarnings("checkstyle:LineLength")
public final class VTestPojo extends ValidatableObject<TestPojo> {

    private final ValidatableValue<Integer> id;

    private final VNestedPojo nested;

    private final ValidatableCollection<Integer> articleIds;

    /**
     * Root object constructor.
     */
    public VTestPojo(final TestPojo value) {
        this(ValidatableReference.PATH_ROOT, value, ValidationContext.getInstance());

        Checks.nonNull(value, "value");
    }

    /**
     * Nested object constructor.
     */
    VTestPojo(final String path, final TestPojo value, final ValidationContext ctx) {
        super(path, value, ctx);

        this.id = new ValidatableValue<>(appendPath("id"), safeGet(value, TestPojo::getId), ctx);
        this.nested = new VNestedPojo(appendPath("nested"), safeGet(value, TestPojo::getNested), ctx);
        this.articleIds = new ValidatableCollection<>(appendPath("articleIds"), safeGet(value, TestPojo::getArticleIds), ctx);
    }

    public ValidatableValue<Integer> getId() {
        return id;
    }

    public VNestedPojo getNested() {
        return nested;
    }

    public ValidatableCollection<Integer> getArticleIds() {
        return articleIds;
    }
}
