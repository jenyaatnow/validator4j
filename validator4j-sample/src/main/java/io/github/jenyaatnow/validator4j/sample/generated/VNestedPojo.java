package io.github.jenyaatnow.validator4j.sample.generated;

import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidatableValue;
import io.github.jenyaatnow.validator4j.core.ValidationContext;
import io.github.jenyaatnow.validator4j.sample.source.NestedPojo;
import io.github.jenyaatnow.validator4j.util.Checks;

import java.lang.annotation.ElementType;

public final class VNestedPojo extends ValidatableObject<NestedPojo> {

    private final ValidatableValue<Integer> id;

    private final ValidatableCollection<Integer> ids;

    private final ValidatableValue<ElementType> type;

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

        this.id = new ValidatableValue<>(appendPath("id"), safeGet(value, NestedPojo::getId), ctx);
        this.ids = new ValidatableCollection<>(appendPath("ids"), safeGet(value, NestedPojo::getIds), ctx);
        this.type = new ValidatableValue<>(appendPath("type"), safeGet(value, NestedPojo::getType), ctx);
    }

    public ValidatableValue<Integer> getId() {
        return id;
    }

    public ValidatableCollection<Integer> getIds() {
        return ids;
    }

    public ValidatableValue<ElementType> getType() {
        return type;
    }
}
