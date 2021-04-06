package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

@Getter
public class ExtendedTypeDescriptor extends TypeDescriptor {

    private final Collection<TypeDescriptor> imports;
    private final List<GetterDescriptor> getters;

    public ExtendedTypeDescriptor(@NonNull final String name,
                                  @NonNull final ValidatableType vType,
                                  @NonNull final Collection<TypeDescriptor> imports,
                                  @NonNull final List<GetterDescriptor> getters)
    {
        super(name, vType);
        this.imports = imports;
        this.getters = getters;
    }

    public ExtendedTypeDescriptor(@NonNull final String name,
                                  @NonNull final ValidatableType vType,
                                  @NonNull final List<TypeDescriptor> typeParameters,
                                  @NonNull final Collection<TypeDescriptor> imports,
                                  @NonNull final List<GetterDescriptor> getters)
    {
        super(name, vType, typeParameters);
        this.imports = imports;
        this.getters = getters;
    }
}
