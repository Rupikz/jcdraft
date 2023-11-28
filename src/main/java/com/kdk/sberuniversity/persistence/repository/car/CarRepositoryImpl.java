package com.kdk.sberuniversity.persistence.repository.car;

import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.PageImpl;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.persistence.repository.car.dao.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public final class CarRepositoryImpl implements CarRepository {

    private static int ID_SEQUENCE;
    private static final List<CarDAO> CARS_REPOSITORY;

    static {
        CARS_REPOSITORY = new ArrayList<>(List.of(
                new CarDAO(getId(), UUID.randomUUID(), "lexus", "LS", 2020, LocalDate.of(2023, 9, 4), new BodyDAO(CarBodyType.COUPE, Color.BLACK), new EngineDAO(CarEngineType.PETROL, 200, 200, 200), new TransmissionDAO(CarTransmissionType.AUTOMATIC, 5), CarDriveType.REAR, new SalonDAO(Color.BLACK, CarInteriorUpholsteryType.LEATHER), CarState.AVAILABLE),
                new CarDAO(getId(), UUID.randomUUID(), "lexus", "LX", 2021, LocalDate.of(2022, 8, 14), new BodyDAO(CarBodyType.SEDAN, Color.BLACK), new EngineDAO(CarEngineType.PETROL, 210, 200, 200), new TransmissionDAO(CarTransmissionType.AUTOMATIC, 5), CarDriveType.REAR, new SalonDAO(Color.BLACK, CarInteriorUpholsteryType.LEATHER), CarState.AVAILABLE),
                new CarDAO(getId(), UUID.randomUUID(), "volkswagen", "golf", 1983, LocalDate.of(1984, 1, 4), new BodyDAO(CarBodyType.WAGON, Color.WHITE), new EngineDAO(CarEngineType.PETROL, 200, 200, 200), new TransmissionDAO(CarTransmissionType.MANUAL, 5), CarDriveType.FRONT, new SalonDAO(Color.BLACK, CarInteriorUpholsteryType.NYLON), CarState.RESERVED),
                new CarDAO(getId(), UUID.randomUUID(), "volkswagen", "jetta", 1988, LocalDate.of(1991, 2, 25), new BodyDAO(CarBodyType.COUPE, Color.BLACK), new EngineDAO(CarEngineType.PETROL, 200, 200, 200), new TransmissionDAO(CarTransmissionType.MANUAL, 5), CarDriveType.FRONT, new SalonDAO(Color.BLACK, CarInteriorUpholsteryType.NYLON), CarState.AVAILABLE)
        ));
    }

    private static int getId() {
        return ++ID_SEQUENCE;
    }

    private static int getIdxOf(CarDAO carDAO) {
        return CARS_REPOSITORY.indexOf(
                CARS_REPOSITORY
                        .stream()
                        .filter(elem -> elem.getCarId().equals(carDAO.getCarId()))
                        .findFirst()
                        .get());
    }

    public List<CarDAO> getAll() {
        return CARS_REPOSITORY;
    }

    public Page<CarDAO> getAll(Pageable pageable, List<Predicate<CarDAO>> filters) {
        List<CarDAO> total = CARS_REPOSITORY.stream()
                .filter(filters.stream()
                        .reduce(x -> true, Predicate::and))
                .toList();
        List<CarDAO> content = total.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getSize())
                .toList();
        return new PageImpl<>(content, pageable, total.size());
    }

    public Optional<CarDAO> getById(UUID carId) {
        return CARS_REPOSITORY.stream()
                .filter(elem -> elem.getCarId().equals(carId))
                // NOTE: копия сущности, чтоб нельзя было изменить ее по ссылке, не знаю на сколько это нужно по заданию, но пока сделал только для поиска по id
                .map(CarDAO::new)
                .findFirst();
    }

    @Override
    public CarDAO save(CarDAO carDAO) {
        carDAO.setId(getId());
        carDAO.setCarId(UUID.randomUUID());
        CARS_REPOSITORY.add(carDAO);

        return CARS_REPOSITORY.get(CARS_REPOSITORY.size() - 1);
    }

    @Override
    public CarDAO update(CarDAO carDAO) {
        CarDAO existingRecord = getById(carDAO.getCarId())
                .orElseThrow(() -> new NotFoundException(String.format("Car entity with id = %s not found", carDAO.getCarId())));

        existingRecord.setBrand(carDAO.getBrand());
        existingRecord.setModel(carDAO.getModel());
        existingRecord.setReleaseYear(carDAO.getReleaseYear());
        existingRecord.setDateOfReceiptIntoStock(carDAO.getDateOfReceiptIntoStock());
        existingRecord.setBody(carDAO.getBody());
        existingRecord.setEngine(carDAO.getEngine());
        existingRecord.setTransmission(carDAO.getTransmission());
        existingRecord.setDrive(carDAO.getDrive());
        existingRecord.setSalon(carDAO.getSalon());
        existingRecord.setState(carDAO.getState());
        CARS_REPOSITORY.set(getIdxOf(carDAO), existingRecord);

        return getById(carDAO.getCarId())
                .orElseThrow(() -> new NotFoundException(String.format("Car entity with id = %s not found", carDAO.getCarId())));
    }

    @Override
    public void delete(UUID carId) {
        CarDAO existingRecord = getById(carId)
                .orElseThrow(() -> new NotFoundException(String.format("Car entity with id = %s not found", carId)));
        CARS_REPOSITORY.remove(getIdxOf(existingRecord));
    }

}
