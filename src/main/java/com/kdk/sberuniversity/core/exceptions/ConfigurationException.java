package com.kdk.sberuniversity.core.exceptions;

public abstract class ConfigurationException extends AppException {

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}
