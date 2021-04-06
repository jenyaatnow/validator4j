package io.github.jenyaatnow.validator4j.sample.generated;

import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableInteger;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidationContext;
import io.github.jenyaatnow.validator4j.sample.source.NestedPojo;
import io.github.jenyaatnow.validator4j.util.Checks;

public final class VNestedPojo extends ValidatableObject<NestedPojo> {

    private final ValidatableInteger id;

    private final ValidatableCollection<Integer, ValidatableInteger> ids;

    /**
     * Root object constructor.
     */
    public VNestedPojo(final NestedPojo value) {
        this(ValidatableReference.PATH_ROOT, value, ValidationContext.getInstance());

        Checks.nonNull(value, "value");
    }

    /**
     * Nested object constructor.
     */
    VNestedPojo(final String path, final NestedPojo value, final ValidationContext ctx) {
        super(path, value, ctx);

        this.id = new ValidatableInteger(appendPath("id"), safeGet(value, NestedPojo::getId), ctx);
        this.ids = new ValidatableCollection<>(appendPath("ids"), safeGet(value, NestedPojo::getIds), ctx);
    }

    public ValidatableInteger getId() {
        return id;
    }

    public ValidatableCollection<Integer, ValidatableInteger> getIds() {
        return ids;
    }
}
