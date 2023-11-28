package com.kdk.sberuniversity.api.mappers.order;

import com.kdk.sberuniversity.api.constants.ordercontroller.OrderAllowedQueryParams;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderStatus;
import com.kdk.sberuniversity.persistence.services.order.dto.OrderFilter;

import java.util.Map;

public final class OrderQueryMapperImpl implements OrderQueryMapper {

    public OrderFilter mapToOrderFilter(Map<String, String> query) {
        String managerName = query.get(OrderAllowedQueryParams.FIND_BY_MANAGER_NAME.getQueryParam());
        OrderStatus status = query.containsKey(OrderAllowedQueryParams.FIND_BY_STATUS.getQueryParam())
                ? OrderStatus.valueOf(query.get(OrderAllowedQueryParams.FIND_BY_STATUS.getQueryParam()))
                : null;

        return new OrderFilter(managerName, status);
    }

}
