package com.kdk.sberuniversity.host;

import com.kdk.sberuniversity.api.constants.HttpStatus;
import com.kdk.sberuniversity.api.controller.ControllersFactory;
import com.kdk.sberuniversity.api.services.ResponseSenderService;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapterImpl;
import com.kdk.sberuniversity.core.exceptions.http.BadRequestException;
import com.kdk.sberuniversity.core.exceptions.http.MethodNotAllowedException;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.core.exceptions.http.UnsupportedMediaTypeException;
import com.kdk.sberuniversity.logging.LoggerHolder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public final class HttpHandlerMediator implements HttpHandler {

    private final ControllersFactory controllersFactory;
    private final ResponseSenderService responseSenderService;

    public HttpHandlerMediator(ControllersFactory controllersFactory, ResponseSenderService responseSenderService) {
        this.controllersFactory = controllersFactory;
        this.responseSenderService = responseSenderService;
    }

    public void handle(HttpExchange httpExchange) {
        HttpExchangeAdapter adapter = new HttpExchangeAdapterImpl(httpExchange);
        LoggerHolder.apiLogger.debug("Request " + httpExchange.getRequestMethod() + " '" + httpExchange.getRequestURI() + "'");
        handleException(adapter, () -> {
            String response = controllersFactory.get(adapter).handleRequest(adapter);
            if (response != null) responseSenderService.sendOk(response, adapter);
            else responseSenderService.sendVoidOK(adapter);
        });
    }

    public void handleException(HttpExchangeAdapter adapter, Runnable requestHandler) {
        try {
            requestHandler.run();
        } catch (MethodNotAllowedException ex) {
            responseSenderService.sendError(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), adapter);
        } catch (BadRequestException | IllegalArgumentException ex) {
            responseSenderService.sendError(HttpStatus.BAD_REQUEST, ex.getMessage(), adapter);
        } catch (UnsupportedMediaTypeException ex) {
            responseSenderService.sendError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage(), adapter);
        } catch (NotFoundException ex) {
            responseSenderService.sendError(HttpStatus.NOT_FOUND, ex.getMessage(), adapter);
        } catch (Exception ex) {
            responseSenderService.sendError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), adapter);
        }
    }

}
