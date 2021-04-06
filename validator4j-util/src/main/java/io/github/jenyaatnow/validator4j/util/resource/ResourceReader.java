package io.github.jenyaatnow.validator4j.util.resource;

public interface ResourceReader {

    ResourceReader instance = new BaseResourceReader();

    String readResourceAsString(ResourcePath resourcePath);
}
