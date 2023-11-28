package com.kdk.sberuniversity.persistence.repository.car.dao;

public final class BodyDAO {

    private CarBodyType type;
    private Color color;

    public BodyDAO(BodyDAO body) {
        this(body.getType(), body.getColor());
    }

    public BodyDAO(CarBodyType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public CarBodyType getType() {
        return type;
    }

    public void setType(CarBodyType type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
