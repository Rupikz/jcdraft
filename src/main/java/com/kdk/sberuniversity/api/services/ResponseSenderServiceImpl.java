package com.kdk.sberuniversity.api.services;

import com.kdk.sberuniversity.api.constants.HttpStatus;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;
import com.kdk.sberuniversity.core.exceptions.HttpServerException;
import com.kdk.sberuniversity.logging.LoggerHolder;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class ResponseSenderServiceImpl implements ResponseSenderService {

    @Override
    public void sendOk(String msg, HttpExchangeAdapter adapter) {
        sendResponse(HttpStatus.OK.value(), msg, adapter);
    }

    @Override
    public void sendVoidOK(HttpExchangeAdapter adapter) {
        sendResponse(HttpStatus.NO_CONTENT.value(), "", adapter);
    }

    @Override
    public void sendError(HttpStatus httpStatus, String msg, HttpExchangeAdapter adapter) {
        sendResponse(httpStatus.value(), msg, adapter);
    }

    private void sendResponse(int status, String message, HttpExchangeAdapter adapter) {
        try {
            adapter.getResponseHeaders().put("Content-Type", List.of("application/xml; charset=utf-8"));
            adapter.setResponse(status, (message == null || message.length() == 0) ? -1 : message.getBytes().length);

            OutputStream os = adapter.getResponseBody();
            if (message != null)
                os.write(message.getBytes(StandardCharsets.UTF_8));
            else
                os.write(0);
            adapter.close();
            LoggerHolder.apiLogger.debug("Response " + adapter.getRequestMethod() + " '" + adapter.getRequestURI() + "' status=" + status + ", message='" + message + "'");
        } catch (IOException ex) {
            throw new HttpServerException("Couldn't send response");
        } finally {
            adapter.close();
        }
    }

}
