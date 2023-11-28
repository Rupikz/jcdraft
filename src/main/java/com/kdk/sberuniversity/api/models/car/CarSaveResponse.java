package com.kdk.sberuniversity.api.models.car;

import com.kdk.sberuniversity.persistence.repository.car.dao.CarDriveType;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarState;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.UUID;

@XmlRootElement(name = "car")
public final class CarSaveResponse extends Car {

    public CarSaveResponse(UUID id, String brand, String model, Integer releaseYear, LocalDate dateOfReceiptIntoStock, Body body, Engine engine, Transmission transmission, CarDriveType drive, Salon salon, CarState state) {
        super(id, brand, model, releaseYear, dateOfReceiptIntoStock, body, engine, transmission, drive, salon, state);
    }

    public CarSaveResponse(String brand, String model, Integer releaseYear, LocalDate dateOfReceiptIntoStock, Body body, Engine engine, Transmission transmission, CarDriveType drive, Salon salon, CarState state) {
        super(brand, model, releaseYear, dateOfReceiptIntoStock, body, engine, transmission, drive, salon, state);
    }

    public CarSaveResponse() {
    }

}
