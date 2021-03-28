package com.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.lang.model.element.TypeElement;

@Getter
@RequiredArgsConstructor
public class GetterDetails {

    @NonNull private final String name;
    @NonNull private final ValidatableType vType;
    @NonNull private final TypeElement enclosingType;

    public String getFieldName() {
        final var fieldName = name.substring(3);
        final var firstCharacter = fieldName.substring(0, 1).toLowerCase();
        final var rest = fieldName.substring(1);
        return firstCharacter + rest;
    }
}
