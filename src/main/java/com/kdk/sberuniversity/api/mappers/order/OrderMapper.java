package com.kdk.sberuniversity.api.mappers.order;

import com.kdk.sberuniversity.api.models.order.Order;
import com.kdk.sberuniversity.api.models.order.OrderSaveRequest;
import com.kdk.sberuniversity.api.models.order.OrderSaveResponse;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderDAO;

import java.util.List;

public interface OrderMapper {

    OrderSaveResponse mapToOrderSaveResponse(OrderDAO orderDAO);
    List<Order> mapToOrderList(List<OrderDAO> orders);
    Order mapToOrder(OrderDAO orderDAO);
    OrderDAO mapToOrderDAO(Order order);
    OrderDAO mapToOrderDAO(OrderSaveRequest order);

}
