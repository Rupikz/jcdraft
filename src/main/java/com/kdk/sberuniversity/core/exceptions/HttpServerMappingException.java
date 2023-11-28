package com.kdk.sberuniversity.core.exceptions;

public final class HttpServerMappingException extends HttpServerException {

    public HttpServerMappingException(String message) {
        super(message);
    }

    public HttpServerMappingException(String message, Throwable cause) {
        super(message, cause);
    }

}
