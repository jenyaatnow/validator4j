package com.validator4j.codegen;

import lombok.NonNull;
import lombok.SneakyThrows;

final class ResourceReaderImpl implements ResourceReader {

    @SneakyThrows
    @Override
    public String readResourceAsString(@NonNull final ResourcePath resourcePath) {
        final var relativePath = resourcePath.getRelativePath();
        try (final var inputStream = ResourceReaderImpl.class.getClassLoader().getResourceAsStream(relativePath)) {
            if (inputStream == null) {
                throw new RuntimeException(String.format("Resource '%s' not found.", relativePath));
            }

            final var bytes = inputStream.readAllBytes();
            final var resourceContent = new String(bytes);

            return resourceContent;
        }
    }
}
