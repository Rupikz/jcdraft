package com.kdk.sberuniversity.core.exceptions.http;

public final class NotFoundException extends AppApiException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
