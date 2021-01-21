package com.thetechmaddy.authservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    public static <T> T parseJson(ObjectMapper objectMapper, String json, Class<T> type) {
        try {
            return json == null ? null : objectMapper.readValue(json, type);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Encountered exception while parsing JSON from content - %s, errorMessage - %s", json, ex.getMessage()), ex);
        }
    }

}
