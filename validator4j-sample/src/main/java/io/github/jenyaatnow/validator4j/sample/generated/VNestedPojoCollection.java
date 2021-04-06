package io.github.jenyaatnow.validator4j.sample.generated;

import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.core.ValidationContext;
import io.github.jenyaatnow.validator4j.sample.source.NestedPojo;

import java.util.Collection;

public final class VNestedPojoCollection extends ValidatableCollection<NestedPojo, VNestedPojo> {

    public VNestedPojoCollection(final String path, final Collection<NestedPojo> value, final ValidationContext ctx) {
        super(
            path,
            value,
            (p, v) -> new VNestedPojo(p, v, ctx),
            ctx
        );
    }
}
