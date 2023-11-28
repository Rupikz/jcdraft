package com.kdk.sberuniversity.api.constants.carcontroller;

public enum CarAllowedQueryParams {

    FIND_BY_ID("id"),
    FIND_BY_MODEL("model"),
    FIND_BY_RELEASE_YEAR("releaseYear"),
    FIND_BY_ENGINE_TYPE("engineType"),
    FIND_BY_TRANSMISSION_TYPE("transmissionType"),
    FIND_BY_DRIVE_TYPE("driveType"),

    PAGINATION_OFFSET("offset"),
    PAGINATION_SIZE("size");

    private final String queryParam;

    CarAllowedQueryParams(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getQueryParam() {
        return queryParam;
    }

}
