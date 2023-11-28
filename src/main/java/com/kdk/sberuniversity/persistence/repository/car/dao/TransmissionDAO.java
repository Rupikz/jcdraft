package com.kdk.sberuniversity.persistence.repository.car.dao;

public final class TransmissionDAO {

    private CarTransmissionType type;
    private Integer numberOfGears;

    public TransmissionDAO(TransmissionDAO transmission) {
        this(transmission.getType(), transmission.getNumberOfGears());
    }

    public TransmissionDAO(CarTransmissionType type, Integer numberOfGears) {
        this.type = type;
        this.numberOfGears = numberOfGears;
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
