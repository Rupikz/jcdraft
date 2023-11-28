package com.kdk.sberuniversity.api.constants.deadendcontroller;

public enum DeadEndAllowedQueryParams {

    FIND_BY_ID("id");

    private final String queryParam;

    DeadEndAllowedQueryParams(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getQueryParam() {
        return queryParam;
    }

}
