package com.kdk.sberuniversity.persistence.repository.order;

import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.PageImpl;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderDAO;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public final class OrderRepositoryImpl implements OrderRepository {

    private static int ID_SEQUENCE;
    private static final List<OrderDAO> ORDERS_REPOSITORY;

    static {
        ORDERS_REPOSITORY = new ArrayList<>(List.of(
                new OrderDAO(getId(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), OrderStatus.NEW, "Медведев Давид Евсеевич", "Харитонов Сергей Анатольевич", new ArrayList<>()),
                new OrderDAO(getId(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), OrderStatus.COMPLETED, "Денисов Станислав Борисович", "Уваров Аркадий Николаевич", new ArrayList<>())
        ));
    }

    private static int getId() {
        return ++ID_SEQUENCE;
    }

    private static int getIdxOf(OrderDAO orderDAO) {
        return ORDERS_REPOSITORY.indexOf(
                ORDERS_REPOSITORY
                        .stream()
                        .filter(elem -> elem.getOrderId().equals(orderDAO.getOrderId()))
                        .findFirst()
                        .get());
    }

    public List<OrderDAO> getAll() {
        return ORDERS_REPOSITORY;
    }

    public Page<OrderDAO> getAll(Pageable pageable, List<Predicate<OrderDAO>> filters) {
        List<OrderDAO> total = ORDERS_REPOSITORY.stream()
                .filter(filters.stream()
                        .reduce(x -> true, Predicate::and))
                .toList();

        List<OrderDAO> content = total.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getSize())
                .toList();
        return new PageImpl<>(content, pageable, total.size());
    }

    public Optional<OrderDAO> getById(UUID orderId) {
        return ORDERS_REPOSITORY.stream()
                .filter(elem -> elem.getOrderId().equals(orderId))
                .map(OrderDAO::new)
                .findFirst();
    }

    @Override
    public OrderDAO save(OrderDAO orderDAO) {
        orderDAO.setId(getId());
        orderDAO.setOrderId(UUID.randomUUID());
        LocalDateTime now = LocalDateTime.now();
        orderDAO.setCreatedAt(now);
        orderDAO.setUpdatedAt(now);
        ORDERS_REPOSITORY.add(orderDAO);

        return ORDERS_REPOSITORY.get(ORDERS_REPOSITORY.size() - 1);
    }

    @Override
    public OrderDAO update(OrderDAO orderDAO) {
        OrderDAO existingRecord = getById(orderDAO.getOrderId())
                .orElseThrow(() -> new NotFoundException(String.format("Order entity with id = %s not found", orderDAO.getOrderId())));

        existingRecord.setUpdatedAt(LocalDateTime.now());
        existingRecord.setStatus(orderDAO.getStatus());
        existingRecord.setManagerName(orderDAO.getManagerName());
        existingRecord.setClientName(orderDAO.getClientName());
        existingRecord.setCarsId(orderDAO.getCarsId());
        ORDERS_REPOSITORY.set(getIdxOf(orderDAO), existingRecord);

        return getById(orderDAO.getOrderId())
                .orElseThrow(() -> new NotFoundException(String.format("Order entity with id = %s not found", orderDAO.getOrderId())));
    }

    @Override
    public void delete(UUID orderId) {
        OrderDAO existingRecord = getById(orderId)
                .orElseThrow(() -> new NotFoundException(String.format("Order entity with id = %s not found", orderId)));
        ORDERS_REPOSITORY.remove(getIdxOf(existingRecord));
    }

}
