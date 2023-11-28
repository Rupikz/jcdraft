package com.kdk.sberuniversity.api.controller;

import com.kdk.sberuniversity.api.constants.AllowedMethods;
import com.kdk.sberuniversity.api.constants.deadendcontroller.DeadEndAllowedQueryParams;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;

import java.util.List;
import java.util.Map;

import static com.kdk.sberuniversity.core.constants.ExceptionConstants.ENDPOINT_DOES_NOT_EXIST;

public final class DeadEndController extends Controller<DeadEndAllowedQueryParams> {

    public DeadEndController(Map<String, Map<AllowedMethods, List<DeadEndAllowedQueryParams>>> allowedRoutes) {
        super(allowedRoutes);
    }

    @Override
    public Object findAll(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public Object findPage(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public Object findByCriteria(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public Object save(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public void delete(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public void reserve(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public void cancelReserve(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public Object isReserved(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

}
