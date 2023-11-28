package com.kdk.sberuniversity.api.constants.ordercontroller;

public enum OrderAllowedQueryParams {

    FIND_BY_ID("id"),
    FIND_BY_MANAGER_NAME("managerName"),
    FIND_BY_STATUS("status"),

    PAGINATION_OFFSET("offset"),
    PAGINATION_SIZE("size");

    private final String queryParam;

    OrderAllowedQueryParams(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getQueryParam() {
        return queryParam;
    }

}
