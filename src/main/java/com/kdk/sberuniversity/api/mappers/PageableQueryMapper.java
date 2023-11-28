package com.kdk.sberuniversity.api.mappers;

import com.kdk.sberuniversity.api.pagination.PageRequest;

import java.util.Map;

public interface PageableQueryMapper {

    PageRequest mapToPageRequest(Map<String, String> query);

}
