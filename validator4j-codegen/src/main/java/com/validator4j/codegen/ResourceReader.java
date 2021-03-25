package com.validator4j.codegen;

public interface ResourceReader {

    ResourceReader instance = new ResourceReaderImpl();

    String readResourceAsString(ResourcePath resourcePath);
}
