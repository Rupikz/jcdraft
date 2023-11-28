package com.kdk.sberuniversity.persistence.services.car.dto;

import com.kdk.sberuniversity.persistence.repository.car.dao.CarDriveType;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarEngineType;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarTransmissionType;

public final class CarFilter {

    private String model;
    private Integer releaseYear;
    private CarEngineType engineType;
    private CarTransmissionType transmissionType;
    private CarDriveType driveType;

    public CarFilter(String model, Integer releaseYear, CarEngineType engineType, CarTransmissionType transmissionType, CarDriveType driveType) {
        this.model = model;
        this.releaseYear = releaseYear;
        this.engineType = engineType;
        this.transmissionType = transmissionType;
        this.driveType = driveType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public CarEngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(CarEngineType engineType) {
        this.engineType = engineType;
    }

    public CarTransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(CarTransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public CarDriveType getDriveType() {
        return driveType;
    }

    public void setDriveType(CarDriveType driveType) {
        this.driveType = driveType;
    }

}
