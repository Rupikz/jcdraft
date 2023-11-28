package com.kdk.sberuniversity.api.controller;

import com.kdk.sberuniversity.api.constants.AllowedMethods;
import com.kdk.sberuniversity.api.services.MarshallingService;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;
import com.kdk.sberuniversity.core.exceptions.HttpServerException;
import com.kdk.sberuniversity.core.exceptions.http.BadRequestException;
import com.kdk.sberuniversity.core.exceptions.http.MethodNotAllowedException;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.core.exceptions.http.UnsupportedMediaTypeException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.kdk.sberuniversity.core.constants.ExceptionConstants.ENDPOINT_DOES_NOT_EXIST;
import static com.kdk.sberuniversity.core.constants.ExceptionConstants.QUERY_PARAMS_NOT_FOUND;

// TODO: переделать, нарушается принцип разделения интерфейсов
public abstract class Controller<E extends Enum<E>> implements CRUDController {

    protected final Map<String, Map<AllowedMethods, List<E>>> supportedRoutes;

    protected Controller(Map<String, Map<AllowedMethods, List<E>>> supportedRoutes) {
        this.supportedRoutes = supportedRoutes;
    }

    public final String handleRequest(HttpExchangeAdapter adapter) {
        checkRequestMediaType(adapter);
        final Map<AllowedMethods, List<E>> supportedMethods = supportedRoutes.get(adapter.getRequestMethod());
        final String requestedMethod = adapter.extractMethodName().toLowerCase(Locale.ROOT);
        if (supportedMethods == null || !isMethodSupported(supportedMethods, requestedMethod)) {
            throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
        }
        Object result = invokeControllerMethod(adapter, AllowedMethods.getEnum(requestedMethod));
        return MarshallingService.marshall(result);
    }

    private boolean isMethodSupported(Map<AllowedMethods, List<E>> supportedMethod, String requestedMethod) {
        AllowedMethods allowedMethods = AllowedMethods.getEnum(requestedMethod);
        return supportedMethod.containsKey(allowedMethods);
    }

    protected String getRequestBody(HttpExchangeAdapter adapter) {
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream bis = new BufferedInputStream(adapter.getRequestBody())) {
            byte[] buff = new byte[1024 * 4];
            int bytesRead;
            while ((bytesRead = bis.read(buff)) != -1) {
                String payloadChunk = new String(buff, 0, bytesRead);
                sb.append(payloadChunk);
            }
        } catch (IOException ex) {
            throw new HttpServerException("Failed to get request body", ex);
        }
        return sb.toString();
    }

    protected void checkQueryParamsPairing(HttpExchangeAdapter adapter) {
        Map<String, String> queryParams = adapter.extractQuery();
        if (queryParams.isEmpty()) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }
    }

    private void checkRequestMediaType(HttpExchangeAdapter adapter) {
        List<String> contentType = adapter.getRequestHeaders().get("Content-Type");
        if (contentType == null || !contentType.contains("application/xml")) {
            throw new UnsupportedMediaTypeException("Unsupported media type");
        }
    }

    private Object invokeControllerMethod(HttpExchangeAdapter adapter, AllowedMethods methodToInvoke) {
        switch (methodToInvoke) {
            case LIST:
                return findAll(adapter);
            case PAGE:
                return findPage(adapter);
            case FIND_BY_CRITERIA:
                checkQueryParamsPairing(adapter);
                return findByCriteria(adapter);
            case SAVE:
                return save(adapter);
            case DELETE:
                delete(adapter);
                return null;
            case RESERVE:
                checkQueryParamsPairing(adapter);
                reserve(adapter);
                return null;
            case CANCEL_RESERVE:
                checkQueryParamsPairing(adapter);
                cancelReserve(adapter);
                return null;
            case IS_RESERVED:
                checkQueryParamsPairing(adapter);
                return isReserved(adapter);
            default:
                throw new MethodNotAllowedException("Method not allowed");
        }
    }

}