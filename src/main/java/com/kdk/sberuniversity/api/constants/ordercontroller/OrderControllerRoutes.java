package com.kdk.sberuniversity.api.constants.ordercontroller;

import com.kdk.sberuniversity.api.constants.AllowedMethods;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface OrderControllerRoutes {

    Map<String, Map<AllowedMethods, List<OrderAllowedQueryParams>>> SUPPORTED_ROUTES = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("GET", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.LIST, Collections.emptyList()),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.PAGE, List.of(OrderAllowedQueryParams.PAGINATION_OFFSET, OrderAllowedQueryParams.PAGINATION_SIZE)),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.FIND_BY_CRITERIA, List.of(OrderAllowedQueryParams.FIND_BY_ID))
            )),
            new AbstractMap.SimpleEntry<>("PUT", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.SAVE, Collections.emptyList())
            )),
            new AbstractMap.SimpleEntry<>("DELETE", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.DELETE, List.of(OrderAllowedQueryParams.FIND_BY_ID))
            ))
    );

}
