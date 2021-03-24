package ${outcome.package};
    ${outcome.
    .validator4j}
    ${outcome.
    .root}
    ${outcome.
    .nested}


public final class V${outcome.type.root} extends ValidatableObject<${outcome.type.root}> {

    ${outcome.fields}

    /**
     * Root object constructor.
     */
    public V${outcome.type.root}(final ${outcome.type.root} value) {
        this(ValidatableReference.PATH_ROOT, value, ErrorsContainer.getErrorsContainer());

        Objects.requireNonNull(value, "Validated object must not be null");
    }

    /**
     * Nested object constructor.
     */
    V${outcome.type.root}(final String path, final ${outcome.type.root} value, final ErrorsContainer errors) {
        super(path, value, errors);

        ${outcome.constructor.assignments}
    }

    ${outcome.getters}
}
