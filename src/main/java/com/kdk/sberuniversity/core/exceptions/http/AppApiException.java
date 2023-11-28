package com.kdk.sberuniversity.core.exceptions.http;

import com.kdk.sberuniversity.core.exceptions.AppException;

public abstract class AppApiException extends AppException {

    public AppApiException(String message) {
        super(message);
    }

    public AppApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
