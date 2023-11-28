package com.kdk.sberuniversity.api.models.order;

import com.kdk.sberuniversity.api.adapters.LocalDateTimeAdapter;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderStatus;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order", propOrder = {
        "id", "createdAt", "updatedAt", "status", "managerName", "clientName", "carsId"
})
@XmlRootElement(name = "order")
public class Order {

    private UUID id;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime createdAt;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime updatedAt;
    private OrderStatus status;
    private String managerName;
    private String clientName;
    @XmlElement(name = "carId")
    @XmlElementWrapper(name = "carsId")
    private List<UUID> carsId;

    public Order(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, String managerName, String clientName, List<UUID> carsId) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.managerName = managerName;
        this.clientName = clientName;
        this.carsId = carsId;
    }

    public Order(LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, String managerName, String clientName, List<UUID> carsId) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.managerName = managerName;
        this.clientName = clientName;
        this.carsId = carsId;
    }

    public Order() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
