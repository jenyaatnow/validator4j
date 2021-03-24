package com.validator4j.codegen;

import java.io.IOException;

final class ResourceReader {

    public static String readResourceAsString(final String relativePath) {
        Checks.nonNull(relativePath, "relativePath");

        try (final var inputStream = ResourceReader.class.getClassLoader().getResourceAsStream(relativePath)) {
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
