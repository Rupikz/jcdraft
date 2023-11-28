package com.kdk.sberuniversity.core.exceptions.http;

public final class MethodNotAllowedException extends AppApiException {

    public MethodNotAllowedException(String message) {
        super(message);
    }

    public MethodNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

}
