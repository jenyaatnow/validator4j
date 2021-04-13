package io.github.jenyaatnow.validator4j.core;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Basic validation errors storage implementation.
 */
final class ValidationErrors implements ErrorsContainer {

    /**
     * Validation errors storage itself.
     */
    @Getter
    private final List<ValidationError> errors = new ArrayList<>();

    @Override
    public void add(@NonNull final ValidationError error) {
        this.errors.add(error);
        Collections.sort(this.errors);
    }

    @Override
    public void addAll(@NonNull final Collection<ValidationError> errors) {
        this.errors.addAll(errors);
        Collections.sort(this.errors);
    }

    @Override
    public boolean containsErrors() {
        return !errors.isEmpty();
    }
}
