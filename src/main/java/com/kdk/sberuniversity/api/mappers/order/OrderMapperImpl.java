package com.kdk.sberuniversity.api.mappers.order;

import com.kdk.sberuniversity.api.models.order.Order;
import com.kdk.sberuniversity.api.models.order.OrderSaveRequest;
import com.kdk.sberuniversity.api.models.order.OrderSaveResponse;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderDAO;

import java.util.List;
import java.util.stream.Collectors;

public final class OrderMapperImpl implements OrderMapper {

    public List<Order> mapToOrderList(List<OrderDAO> orders) {
        return orders.stream()
                .map(this::mapToOrderSaveResponse)
                .collect(Collectors.toList());
    }

    public OrderSaveResponse mapToOrderSaveResponse(OrderDAO orderDAO) {
        return new OrderSaveResponse(
                orderDAO.getOrderId(),
                orderDAO.getCreatedAt(),
                orderDAO.getUpdatedAt(),
                orderDAO.getStatus(),
                orderDAO.getManagerName(),
                orderDAO.getClientName(),
                orderDAO.getCarsId()
        );
    }

    public OrderDAO mapToOrderDAO(Order order) {
        return new OrderDAO(
                order.getId(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getStatus(),
                order.getManagerName(),
                order.getClientName(),
                order.getCarsId()
        );
    }

    public OrderDAO mapToOrderDAO(OrderSaveRequest order) {
        return new OrderDAO(
                order.getId(),
                null,
                null,
                order.getStatus(),
                order.getManagerName(),
                order.getClientName(),
                order.getCarsId()
        );
    }

    public Order mapToOrder(OrderDAO orderDAO) {
        return new Order(
                orderDAO.getOrderId(),
                orderDAO.getCreatedAt(),
                orderDAO.getUpdatedAt(),
                orderDAO.getStatus(),
                orderDAO.getManagerName(),
                orderDAO.getClientName(),
                orderDAO.getCarsId()
        );
    }

}
