package com.kdk.sberuniversity.api.models.car;

import com.kdk.sberuniversity.persistence.repository.car.dao.CarTransmissionType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transmission", propOrder = {"type", "numberOfGears"})
@XmlRootElement(name = "transmission")
public final class Transmission {

    private CarTransmissionType type;
    private Integer numberOfGears;

    public Transmission(CarTransmissionType type, Integer numberOfGears) {
        this.type = type;
        this.numberOfGears = numberOfGears;
    }

    public Transmission() {
    }

    public CarTransmissionType getType() {
        return type;
    }

    public void setType(CarTransmissionType type) {
        this.type = type;
    }

    public Integer getNumberOfGears() {
        return numberOfGears;
    }

    public void setNumberOfGears(Integer numberOfGears) {
        this.numberOfGears = numberOfGears;
    }

}