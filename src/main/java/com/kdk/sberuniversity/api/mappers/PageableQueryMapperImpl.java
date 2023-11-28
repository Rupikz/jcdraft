package com.kdk.sberuniversity.api.mappers;

import com.kdk.sberuniversity.api.constants.carcontroller.CarAllowedQueryParams;
import com.kdk.sberuniversity.api.pagination.PageRequest;

import java.util.Map;

public final class PageableQueryMapperImpl implements PageableQueryMapper {

    private static final String DEFAULT_PAGE_SIZE = "10";
    private static final String DEFAULT_PAGE_OFFSET = "0";

    public PageRequest mapToPageRequest(Map<String, String> query) {
        String sizeQueryParam = query.getOrDefault(CarAllowedQueryParams.PAGINATION_SIZE.getQueryParam(), DEFAULT_PAGE_SIZE);
        String offsetQueryParam = query.getOrDefault(CarAllowedQueryParams.PAGINATION_OFFSET.getQueryParam(), DEFAULT_PAGE_OFFSET);

        int size = Integer.parseInt(sizeQueryParam);
        int offset = Integer.parseInt(offsetQueryParam);

        return PageRequest.of(offset, size);
    }

}
