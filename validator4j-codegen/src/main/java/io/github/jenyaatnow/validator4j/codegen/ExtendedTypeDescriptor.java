package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

@Getter
public class ExtendedTypeDescriptor extends TypeDescriptor {

    private final Collection<TypeDescriptor> imports;
    private final List<FieldDescriptor> fields;

    public ExtendedTypeDescriptor(@NonNull final String name,
                                  @NonNull final DataType dataType,
                                  @NonNull final Collection<TypeDescriptor> imports,
                                  @NonNull final List<FieldDescriptor> fields)
    {
        super(name, dataType);
        this.imports = imports;
        this.fields = fields;
    }

    public ExtendedTypeDescriptor(@NonNull final String name,
                                  @NonNull final DataType dataType,
                                  @NonNull final List<TypeDescriptor> typeParameters,
                                  @NonNull final Collection<TypeDescriptor> imports,
                                  @NonNull final List<FieldDescriptor> fields)
    {
        super(name, dataType, typeParameters);
        this.imports = imports;
        this.fields = fields;
    }
}
