package com.kdk.sberuniversity.api.mappers.order;

import com.kdk.sberuniversity.persistence.services.order.dto.OrderFilter;

import java.util.Map;

public interface OrderQueryMapper {

    OrderFilter mapToOrderFilter(Map<String, String> query);

}
