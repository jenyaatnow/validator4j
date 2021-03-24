package com.validator4j.sample.generated;

import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.ValidatableCollection;
import com.validator4j.core.ValidatableInteger;
import com.validator4j.core.ValidatableObject;
import com.validator4j.core.ValidatableReference;
import com.validator4j.sample.source.NestedPojo;

import java.util.Objects;

public final class VNestedPojo extends ValidatableObject<NestedPojo> {

    private final ValidatableInteger id;

    private final ValidatableCollection<Integer, ValidatableInteger> ids;

    /**
     * Root object constructor.
     */
    public VNestedPojo(final NestedPojo value) {
        this(ValidatableReference.PATH_ROOT, value, ErrorsContainer.getErrorsContainer());

        Objects.requireNonNull(value, "Validated object must not be null");
    }

    /**
     * Nested object constructor.
     */
    public VNestedPojo(final String path, final NestedPojo value, final ErrorsContainer errors) {
        super(path, value, errors);

        this.id = new ValidatableInteger(appendPath("id"), safeGet(value, NestedPojo::getId), errors);
        this.ids = new ValidatableCollection<>(appendPath("ids"), safeGet(value, NestedPojo::getIds), errors);
    }

    public ValidatableInteger getId() {
        return id;
    }

    public ValidatableCollection<Integer, ValidatableInteger> getIds() {
        return ids;
    }
}
