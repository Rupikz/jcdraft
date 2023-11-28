package com.kdk.sberuniversity.api.constants.personcontroller;

public enum PersonAllowedQueryParams {

    FIND_BY_ID("id"),
    FIND_BY_FIRST_NAME("firstName");

    private final String queryParam;

    PersonAllowedQueryParams(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getQueryParam() {
        return queryParam;
    }

}
