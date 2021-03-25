package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.io.IOException;

final class ResourceReaderImpl implements ResourceReader {

    @Override
    public String readResourceAsString(final ResourcePath resourcePath) {
        Checks.nonNull(resourcePath, "resourcePath");

        final var relativePath = resourcePath.getRelativePath();
        try (final var inputStream = ResourceReaderImpl.class.getClassLoader().getResourceAsStream(relativePath)) {
            if (inputStream == null) {
                throw new RuntimeException(String.format("Resource '%s' not found.", relativePath));
            }

            final var bytes = inputStream.readAllBytes();
            final var resourceContent = new String(bytes);

            return resourceContent;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
