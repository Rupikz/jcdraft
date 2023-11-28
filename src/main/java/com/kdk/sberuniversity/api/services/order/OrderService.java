package com.kdk.sberuniversity.api.services.order;

import com.kdk.sberuniversity.api.models.PageResponse;
import com.kdk.sberuniversity.api.models.order.Order;
import com.kdk.sberuniversity.api.models.order.OrderListResponse;
import com.kdk.sberuniversity.api.models.order.OrderSaveRequest;
import com.kdk.sberuniversity.api.models.order.OrderSaveResponse;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.persistence.services.order.dto.OrderFilter;

import java.util.UUID;

public interface OrderService {

    Order getOneOrThrow(UUID orderId);
    OrderListResponse getList();
    PageResponse<Order> getPage(Pageable pageable, OrderFilter filter);
    OrderSaveResponse save(OrderSaveRequest request);
    void delete(UUID orderId);

}
