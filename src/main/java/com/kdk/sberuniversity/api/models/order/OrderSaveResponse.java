package com.kdk.sberuniversity.api.models.order;

import com.kdk.sberuniversity.persistence.repository.order.dao.OrderStatus;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@XmlRootElement(name = "order")
public final class OrderSaveResponse extends Order {

    public OrderSaveResponse(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, String managerName, String clientName, List<UUID> carsId) {
        super(id, createdAt, updatedAt, status, managerName, clientName, carsId);
    }

    public OrderSaveResponse(LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, String managerName, String clientName, List<UUID> carsId) {
        super(createdAt, updatedAt, status, managerName, clientName, carsId);
    }

    public OrderSaveResponse() {
    }

}
