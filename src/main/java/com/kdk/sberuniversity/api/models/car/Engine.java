package com.kdk.sberuniversity.api.models.car;

import com.kdk.sberuniversity.persistence.repository.car.dao.CarEngineType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "engine", propOrder = {"type", "capacity", "power", "torque"})
@XmlRootElement(name = "engine")
public final class Engine {

    private CarEngineType type;
    private Integer capacity;
    private Integer power;
    private Integer torque;

    public Engine(CarEngineType type, Integer capacity, Integer power, Integer torque) {
        this.type = type;
        this.capacity = capacity;
        this.power = power;
        this.torque = torque;
    }

    public Engine() {
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
