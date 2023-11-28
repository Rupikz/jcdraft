package com.kdk.sberuniversity.core.exceptions.http;

public final class BadRequestException extends AppApiException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
