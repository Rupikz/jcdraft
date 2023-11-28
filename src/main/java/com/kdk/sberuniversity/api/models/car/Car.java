package com.kdk.sberuniversity.api.models.car;

import com.kdk.sberuniversity.api.adapters.LocalDateAdapter;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarDriveType;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarState;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {
        "id", "brand", "model", "releaseYear", "dateOfReceiptIntoStock", "body", "engine", "transmission", "drive", "salon", "state"
})
@XmlRootElement(name = "car")
public class Car {

    private UUID id;
    private String brand;
    private String model;
    private Integer releaseYear;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOfReceiptIntoStock;
    private Body body;
    private Engine engine;
    private Transmission transmission;
    private CarDriveType drive;
    private Salon salon;
    private CarState state;

    public Car(UUID id, String brand, String model, Integer releaseYear, LocalDate dateOfReceiptIntoStock, Body body, Engine engine, Transmission transmission, CarDriveType drive, Salon salon, CarState state) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.releaseYear = releaseYear;
        this.dateOfReceiptIntoStock = dateOfReceiptIntoStock;
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
        this.drive = drive;
        this.salon = salon;
        this.state = state;
    }

    public Car(String brand, String model, Integer releaseYear, LocalDate dateOfReceiptIntoStock, Body body, Engine engine, Transmission transmission, CarDriveType drive, Salon salon, CarState state) {
        this.brand = brand;
        this.model = model;
        this.releaseYear = releaseYear;
        this.dateOfReceiptIntoStock = dateOfReceiptIntoStock;
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
        this.drive = drive;
        this.salon = salon;
        this.state = state;
    }

    public Car() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public LocalDate getDateOfReceiptIntoStock() {
        return dateOfReceiptIntoStock;
    }

    public void setDateOfReceiptIntoStock(LocalDate dateOfReceiptIntoStock) {
        this.dateOfReceiptIntoStock = dateOfReceiptIntoStock;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public CarDriveType getDrive() {
        return drive;
    }

    public void setDrive(CarDriveType drive) {
        this.drive = drive;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }

}
