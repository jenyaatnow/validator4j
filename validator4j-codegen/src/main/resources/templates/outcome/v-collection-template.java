package ${outcome.package};
    ${outcome.
    .root}


public final class V${outcome.type.root}Collection extends ValidatableCollection<${outcome.type.root}, V${outcome.type.root}> {

    public V${outcome.type.root}Collection(final String path, final Collection<${outcome.type.root}> value, final ErrorsContainer errors) {
        super(
            path,
            value,
            (p, v) -> new V${outcome.type.root}(p, v, errors),
            errors
        );
    }
}
