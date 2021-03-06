package io.github.jenyaatnow.validator4j.util.resource;

import io.github.jenyaatnow.validator4j.util.Validator4jException;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

public class BaseResourceReader implements ResourceReader {

    @SneakyThrows
    @Override
    public String readResourceAsString(@NonNull final ResourcePath resourcePath) {
        final var relativePath = resourcePath.getRelativePath();
        try (final var inputStream = BaseResourceReader.class.getClassLoader().getResourceAsStream(relativePath)) {
            if (inputStream == null) {
                throw new Validator4jException(String.format("Resource '%s' not found.", relativePath));
            }

            final var bytes = inputStream.readAllBytes();
            final var resourceContent = new String(bytes, StandardCharsets.UTF_8);

            return resourceContent;
        }
    }
}
