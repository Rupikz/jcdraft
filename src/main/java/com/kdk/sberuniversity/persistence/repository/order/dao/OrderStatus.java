package com.kdk.sberuniversity.persistence.repository.order.dao;

public enum OrderStatus {

    NEW(1),
    RESERVED(2),
    AWAITING_PAYMENT(3),
    PAID(4),
    COMPLETED(5);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

}
