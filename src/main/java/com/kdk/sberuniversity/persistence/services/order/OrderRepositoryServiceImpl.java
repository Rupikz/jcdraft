package com.kdk.sberuniversity.persistence.services.order;

import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.audit.AuditAction;
import com.kdk.sberuniversity.audit.annotation.Auditable;
import com.kdk.sberuniversity.audit.services.AuditService;
import com.kdk.sberuniversity.core.exceptions.http.BadRequestException;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.persistence.repository.order.OrderRepository;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderDAO;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderStatus;
import com.kdk.sberuniversity.persistence.services.order.dto.OrderFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public final class OrderRepositoryServiceImpl implements OrderRepositoryService {

    private final OrderRepository orderRepository;
    private final AuditService auditService;

    public OrderRepositoryServiceImpl(OrderRepository orderRepository, AuditService auditService) {
        this.orderRepository = orderRepository;
        this.auditService = auditService;
    }

    @Override
    public OrderDAO findById(UUID orderId) {
        return orderRepository.getById(orderId).orElse(null);
    }

    @Override
    public OrderDAO findByIdOrThrow(UUID orderId) {
        return orderRepository.getById(orderId)
                .orElseThrow(() -> new NotFoundException(String.format("Order entity with id = %s not found", orderId)));
    }

    @Override
    public List<OrderDAO> findAll() {
        return orderRepository.getAll();
    }

    @Override
    public Page<OrderDAO> findAll(Pageable pageable, OrderFilter filter) {
        List<Predicate<OrderDAO>> filters = new ArrayList<>();

        if (filter.getManagerName() != null)
            filters.add(elem -> elem.getManagerName().equalsIgnoreCase(filter.getManagerName()));
        if (filter.getStatus() != null)
            filters.add(elem -> elem.getStatus().equals(filter.getStatus()));

        return orderRepository.getAll(pageable, filters);
    }

    @Override
    @Auditable
    public OrderDAO save(OrderDAO orderDAO) {
        OrderDAO order;

        if (orderDAO.getOrderId() == null) {
            orderDAO.setStatus(OrderStatus.NEW);
            order = orderRepository.save(orderDAO);
            auditService.sendEvent(order, AuditAction.CREATE);
        } else {
            OrderDAO currentOrder = findByIdOrThrow(orderDAO.getOrderId());
            if (currentOrder.getStatus().equals(OrderStatus.COMPLETED)) {
                throw new BadRequestException("Cannot change 'COMPLETED' status");
            }
            if (orderDAO.getStatus().value() < currentOrder.getStatus().value()) {
                throw new BadRequestException(String.format("Cannot set status '%s' because current status '%s'", orderDAO.getStatus(), currentOrder.getStatus()));
            }

            order = orderRepository.update(orderDAO);
            auditService.sendEvent(order, AuditAction.UPDATE);
        }
        return order;
    }

    @Override
    @Auditable
    public void delete(UUID orderId) {
        OrderDAO order = findByIdOrThrow(orderId);
        auditService.sendEvent(order, AuditAction.DELETE);
        orderRepository.delete(orderId);
    }

}
