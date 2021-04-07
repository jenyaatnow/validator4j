package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

import java.util.Date;

public final class ValidatableDate extends ValidatableReference<Date> {

    public ValidatableDate(@NonNull final String path, final Date value, @NonNull final ValidationContext ctx) {
        super(path, value, ctx);
    }
}
