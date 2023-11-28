package com.kdk.sberuniversity.persistence.repository.order;

import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface OrderRepository {

    List<OrderDAO> getAll();
    Page<OrderDAO> getAll(Pageable pageable, List<Predicate<OrderDAO>> filters);
    Optional<OrderDAO> getById(UUID orderId);
    OrderDAO save(OrderDAO orderDAO);
    OrderDAO update(OrderDAO orderDAO);
    void delete(UUID orderId);

}
