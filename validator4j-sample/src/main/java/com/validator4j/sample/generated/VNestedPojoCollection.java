package com.validator4j.sample.generated;

import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.ValidatableCollection;
import com.validator4j.sample.source.NestedPojo;

import java.util.Collection;

public final class VNestedPojoCollection extends ValidatableCollection<NestedPojo, VNestedPojo> {

    public VNestedPojoCollection(final String path, final Collection<NestedPojo> value, final ErrorsContainer errors) {
        super(
            path,
            value,
            (p, v) -> new VNestedPojo(p, v, errors),
            errors
        );
    }
}
