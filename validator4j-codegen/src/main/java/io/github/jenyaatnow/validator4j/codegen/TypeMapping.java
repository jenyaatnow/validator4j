package io.github.jenyaatnow.validator4j.codegen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class contains the mapping between the particular source type and the corresponding generated type.
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class TypeMapping {

    /**
     * Source type descriptor.
     */
    private final TypeDescriptor sourceType;

    /**
     * Generated type descriptor.
     */
    private final TypeDescriptor validatableType;
}
