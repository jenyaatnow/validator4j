package com.validator4j.util.resource;

import lombok.NonNull;
import lombok.SneakyThrows;

public class BaseResourceReader implements ResourceReader {

    @SneakyThrows
    @Override
    public String readResourceAsString(@NonNull final ResourcePath resourcePath) {
        final var relativePath = resourcePath.getRelativePath();
        try (final var inputStream = BaseResourceReader.class.getClassLoader().getResourceAsStream(relativePath)) {
            if (inputStream == null) {
                throw new RuntimeException(String.format("Resource '%s' not found.", relativePath));
            }

            final var bytes = inputStream.readAllBytes();
            final var resourceContent = new String(bytes);

            return resourceContent;
        }
    }
}
