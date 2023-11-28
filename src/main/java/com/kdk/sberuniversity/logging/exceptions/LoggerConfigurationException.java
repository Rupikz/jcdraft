package com.kdk.sberuniversity.logging.exceptions;

import com.kdk.sberuniversity.core.exceptions.ConfigurationException;

public final class LoggerConfigurationException extends ConfigurationException {

    public LoggerConfigurationException(String message) {
        super(message);
    }

    public LoggerConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}
