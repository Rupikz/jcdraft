package com.kdk.sberuniversity.api.models.order;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orders", propOrder = {"orders"})
@XmlRootElement(name = "orders")
public final class OrderListResponse {

    @XmlElement(name = "order")
    private List<Order> orders;

    public OrderListResponse(List<Order> orders) {
        this.orders = orders;
    }

    public OrderListResponse() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
