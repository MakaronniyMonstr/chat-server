package com.vesko.chatserver.exception;

import java.util.Collections;
import java.util.Map;

public class JsonParsableException extends RuntimeException {
    public JsonParsableException(String message) {
        super(message);
    }

    public Map<String, String> getJsonSerializableMessage() {
        return Collections.singletonMap("error", getMessage());
    }
}
