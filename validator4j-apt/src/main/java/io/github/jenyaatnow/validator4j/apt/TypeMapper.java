package io.github.jenyaatnow.validator4j.apt;

import io.github.jenyaatnow.validator4j.codegen.DataType;
import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.TypeMapping;
import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.NonNull;

import java.util.List;

class TypeMapper {

    public TypeMapping mapToValidatable(@NonNull final TypeDescriptor sourceType) {
        final var sourceDataType = sourceType.getDataType();
        final var sourceV4jClassName = sourceDataType.getVClass().getName();

        if (sourceDataType == DataType.VALUE) {
            final var v4jType = new TypeDescriptor(sourceV4jClassName, DataType.V4J, List.of(sourceType));
            return new TypeMapping(sourceType, v4jType);
        }

        if (sourceDataType == DataType.COLLECTION) {
            final var v4jType = new TypeDescriptor(sourceV4jClassName, DataType.V4J, sourceType.getTypeParameters());
            return new TypeMapping(sourceType, v4jType);
        }

        if (sourceDataType == DataType.VALIDATABLE) {
            final var v4jTypeName = String.format(
                "%s.%s%s",
                sourceType.getPackageName(),
                Validatable.GENERATED_CLASS_PREFIX,
                sourceType.getSimpleName()
            );

            final var v4jType = new TypeDescriptor(v4jTypeName, DataType.V4J);
            return new TypeMapping(sourceType, v4jType);
        }

        return null;
    }
}
