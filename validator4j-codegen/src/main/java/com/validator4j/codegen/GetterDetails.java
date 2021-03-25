package com.validator4j.codegen;

import com.validator4j.util.Checks;

public class GetterDetails {

    private final String name;
    private final ValidatableType vType;
    private final Class<?> enclosingType;

    public GetterDetails(final String name,
                         final ValidatableType vType,
                         final Class<?> enclosingType)
    {
        Checks.nonNull(name, "name");
        Checks.nonNull(vType, "vType");
        Checks.nonNull(enclosingType, "enclosingType");

        this.name = name;
        this.vType = vType;
        this.enclosingType = enclosingType;
    }

    public String getName() {
        return name;
    }

    public ValidatableType getVType() {
        return vType;
    }

    public Class<?> getEnclosingType() {
        return enclosingType;
    }

    public String getFieldName() {
        final var fieldName = name.substring(3);
        final var firstCharacter = fieldName.substring(0, 1).toLowerCase();
        final var rest = fieldName.substring(1);
        return firstCharacter + rest;
    }
}
