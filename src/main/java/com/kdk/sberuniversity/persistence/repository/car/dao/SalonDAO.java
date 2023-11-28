package com.kdk.sberuniversity.persistence.repository.car.dao;

public final class SalonDAO {

    private Color color;
    private CarInteriorUpholsteryType type;

    public SalonDAO(SalonDAO salon) {
        this(salon.getColor(), salon.getType());
    }

    public SalonDAO(Color color, CarInteriorUpholsteryType type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public CarInteriorUpholsteryType getType() {
        return type;
    }

    public void setType(CarInteriorUpholsteryType type) {
        this.type = type;
    }

}
