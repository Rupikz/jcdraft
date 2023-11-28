package com.kdk.sberuniversity.persistence.repository.order.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public final class OrderDAO {

    private Integer id;
    private UUID orderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private OrderStatus status;
    private String managerName;
    private String clientName;
    private List<UUID> carsId;

    public OrderDAO(OrderDAO orderDAO) {
        this(orderDAO.getId(), orderDAO.getOrderId(), orderDAO.getCreatedAt(), orderDAO.getUpdatedAt(), orderDAO.getStatus(), orderDAO.getManagerName(), orderDAO.getClientName(), orderDAO.getCarsId());
    }

    public OrderDAO(Integer id, UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, String managerName, String clientName, List<UUID> carsId) {
        this.id = id;
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.managerName = managerName;
        this.clientName = clientName;
        this.carsId = carsId;
    }

    public OrderDAO(UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, String managerName, String clientName, List<UUID> carsId) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.managerName = managerName;
        this.clientName = clientName;
        this.carsId = carsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<UUID> getCarsId() {
        return carsId;
    }

    public void setCarsId(List<UUID> carsId) {
        this.carsId = carsId;
    }

}
