package com.kdk.sberuniversity.persistence.services.order;

import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderDAO;
import com.kdk.sberuniversity.persistence.services.order.dto.OrderFilter;

import java.util.List;
import java.util.UUID;

public interface OrderRepositoryService {

    OrderDAO findById(UUID orderId);
    OrderDAO findByIdOrThrow(UUID orderId);
    List<OrderDAO> findAll();
    Page<OrderDAO> findAll(Pageable pageable, OrderFilter filter);
    OrderDAO save(OrderDAO orderDAO);
    void delete(UUID orderId);

}
