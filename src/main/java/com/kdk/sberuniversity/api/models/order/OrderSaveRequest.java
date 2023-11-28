package com.kdk.sberuniversity.api.models.order;

import com.kdk.sberuniversity.persistence.repository.order.dao.OrderStatus;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@XmlRootElement(name = "order")
public final class OrderSaveRequest extends Order {

    public OrderSaveRequest(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, String managerName, String clientName, List<UUID> carsId) {
        super(id, null, null, status, managerName, clientName, carsId);
    }

    public OrderSaveRequest(LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, String managerName, String clientName, List<UUID> carsId) {
        super(null, null, status, managerName, clientName, carsId);
    }

    public OrderSaveRequest() {
    }

}
