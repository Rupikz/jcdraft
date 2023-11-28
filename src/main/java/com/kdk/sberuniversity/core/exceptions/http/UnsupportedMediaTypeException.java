package com.kdk.sberuniversity.core.exceptions.http;

public final class UnsupportedMediaTypeException extends AppApiException {

    public UnsupportedMediaTypeException(String message) {
        super(message);
    }

    public UnsupportedMediaTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
