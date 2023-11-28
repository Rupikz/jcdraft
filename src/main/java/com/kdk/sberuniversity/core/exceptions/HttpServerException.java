package com.kdk.sberuniversity.core.exceptions;

public class HttpServerException extends AppException {

    public HttpServerException(String message) {
        super(message);
    }

    public HttpServerException(String message, Throwable cause) {
        super(message, cause);
    }

}
