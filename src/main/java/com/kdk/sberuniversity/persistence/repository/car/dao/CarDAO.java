package com.kdk.sberuniversity.persistence.repository.car.dao;

import java.time.LocalDate;
import java.util.UUID;

public final class CarDAO {

    private Integer id;
    private UUID carId;
    private String brand;
    private String model;
    private Integer releaseYear;
    private LocalDate dateOfReceiptIntoStock;
    private BodyDAO body;
    private EngineDAO engine;
    private TransmissionDAO transmission;
    private CarDriveType drive;
    private SalonDAO salon;
    private CarState state;

    public CarDAO(CarDAO carDAO) {
        this(carDAO.getId(), carDAO.getCarId(), carDAO.getBrand(), carDAO.getModel(), carDAO.getReleaseYear(), carDAO.getDateOfReceiptIntoStock(), new BodyDAO(carDAO.getBody()), new EngineDAO(carDAO.getEngine()), new TransmissionDAO(carDAO.getTransmission()), carDAO.getDrive(), new SalonDAO(carDAO.getSalon()), carDAO.getState());
    }

    public CarDAO(Integer id, UUID carId, String brand, String model, Integer releaseYear, LocalDate dateOfReceiptIntoStock, BodyDAO body, EngineDAO engine, TransmissionDAO transmission, CarDriveType drive, SalonDAO salon, CarState state) {
        this.id = id;
        this.carId = carId;
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

    public CarDAO(UUID carId, String brand, String model, Integer releaseYear, LocalDate dateOfReceiptIntoStock, BodyDAO body, EngineDAO engine, TransmissionDAO transmission, CarDriveType drive, SalonDAO salon, CarState state) {
        this.carId = carId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
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

    public BodyDAO getBody() {
        return body;
    }

    public void setBody(BodyDAO body) {
        this.body = body;
    }

    public EngineDAO getEngine() {
        return engine;
    }

    public void setEngine(EngineDAO engine) {
        this.engine = engine;
    }

    public TransmissionDAO getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionDAO transmission) {
        this.transmission = transmission;
    }

    public CarDriveType getDrive() {
        return drive;
    }

    public void setDrive(CarDriveType drive) {
        this.drive = drive;
    }

    public SalonDAO getSalon() {
        return salon;
    }

    public void setSalon(SalonDAO salon) {
        this.salon = salon;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }

}
