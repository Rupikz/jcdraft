package com.kdk.sberuniversity.api.controller;

import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;

public interface CRUDController {

    Object findAll(HttpExchangeAdapter adapter);
    Object findPage(HttpExchangeAdapter adapter);
    Object findByCriteria(HttpExchangeAdapter adapter);
    Object save(HttpExchangeAdapter adapter);
    void delete(HttpExchangeAdapter adapter);
    void reserve(HttpExchangeAdapter adapter);
    void cancelReserve(HttpExchangeAdapter adapter);
    Object isReserved(HttpExchangeAdapter adapter);

}
