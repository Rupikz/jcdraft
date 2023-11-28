package com.kdk.sberuniversity.api.services;

import com.kdk.sberuniversity.api.constants.HttpStatus;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;

public interface ResponseSenderService {

    void sendOk(String msg, HttpExchangeAdapter adapter);
    void sendVoidOK(HttpExchangeAdapter adapter);
    void sendError(HttpStatus httpStatus, String msg, HttpExchangeAdapter adapter);

}
