package io.github.jenyaatnow.validator4j.sample.generated;

import io.github.jenyaatnow.validator4j.core.ErrorsCollector;
import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.sample.source.NestedPojo;

import java.util.Collection;

public final class VNestedPojoCollection extends ValidatableCollection<NestedPojo, VNestedPojo> {

    public VNestedPojoCollection(final String path, final Collection<NestedPojo> value, final ErrorsCollector errors) {
        super(
            path,
            value,
            (p, v) -> new VNestedPojo(p, v, errors),
            errors
        );
    }
}
