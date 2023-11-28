package com.kdk.sberuniversity.api.constants.carcontroller;

import com.kdk.sberuniversity.api.constants.AllowedMethods;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface CarControllerRoutes {

    Map<String, Map<AllowedMethods, List<CarAllowedQueryParams>>> SUPPORTED_ROUTES = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("GET", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.LIST, Collections.emptyList()),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.PAGE, List.of(CarAllowedQueryParams.PAGINATION_OFFSET, CarAllowedQueryParams.PAGINATION_SIZE, CarAllowedQueryParams.FIND_BY_MODEL, CarAllowedQueryParams.FIND_BY_RELEASE_YEAR, CarAllowedQueryParams.FIND_BY_ENGINE_TYPE, CarAllowedQueryParams.FIND_BY_TRANSMISSION_TYPE, CarAllowedQueryParams.FIND_BY_DRIVE_TYPE)),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.FIND_BY_CRITERIA, List.of(CarAllowedQueryParams.FIND_BY_ID)),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.IS_RESERVED, List.of(CarAllowedQueryParams.FIND_BY_ID))
            )),
            new AbstractMap.SimpleEntry<>("PUT", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.SAVE, Collections.emptyList()),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.RESERVE, List.of(CarAllowedQueryParams.FIND_BY_ID)),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.CANCEL_RESERVE, List.of(CarAllowedQueryParams.FIND_BY_ID))
            )),
            new AbstractMap.SimpleEntry<>("DELETE", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.DELETE, List.of(CarAllowedQueryParams.FIND_BY_ID))
            ))
    );

}
