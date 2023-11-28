package com.kdk.sberuniversity.persistence.services.order.dto;

import com.kdk.sberuniversity.persistence.repository.order.dao.OrderStatus;

public final class OrderFilter {

    private String managerName;
    private OrderStatus status;

    public OrderFilter(String managerName, OrderStatus status) {
        this.managerName = managerName;
        this.status = status;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
