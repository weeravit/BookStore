package com.junebookstore.common.wrapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;

public class JsonWrapper {
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T readValue(String content, TypeReference valueTypeRef) throws IOException {
        return objectMapper.readValue(content, valueTypeRef);
    }

    public <T> T readValue(Reader src, Class<T> valueType) throws IOException {
        return objectMapper.readValue(src, valueType);
    }
}
