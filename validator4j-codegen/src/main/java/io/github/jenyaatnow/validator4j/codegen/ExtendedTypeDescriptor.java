package io.github.jenyaatnow.validator4j.codegen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * Type descriptor with additional type information.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ExtendedTypeDescriptor extends TypeDescriptor {

    /**
     * Collection of types that uses as imports in the type represented by instance of this class.
     */
    private final Collection<TypeDescriptor> imports;

    /**
     * List of the declared fields of this class.
     */
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
