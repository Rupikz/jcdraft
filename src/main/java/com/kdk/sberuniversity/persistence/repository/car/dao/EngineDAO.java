package com.kdk.sberuniversity.persistence.repository.car.dao;

public final class EngineDAO {

    private CarEngineType type;
    private Integer capacity;
    private Integer power;
    private Integer torque;

    public EngineDAO(EngineDAO engine) {
        this(engine.getType(), engine.getCapacity(), engine.getPower(), engine.getTorque());
    }

    public EngineDAO(CarEngineType type, Integer capacity, Integer power, Integer torque) {
        this.type = type;
        this.capacity = capacity;
        this.power = power;
        this.torque = torque;
    }

    public CarEngineType getType() {
        return type;
    }

    public void setType(CarEngineType type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }

}
