package com.kdk.sberuniversity.persistence.services.car;

import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.audit.AuditAction;
import com.kdk.sberuniversity.audit.annotation.Auditable;
import com.kdk.sberuniversity.audit.services.AuditService;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.persistence.repository.car.CarRepository;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarDAO;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarState;
import com.kdk.sberuniversity.persistence.services.car.dto.CarFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public final class CarRepositoryServiceImpl implements CarRepositoryService {

    private final CarRepository carRepository;
    private final AuditService auditService;

    public CarRepositoryServiceImpl(CarRepository carRepository, AuditService auditService) {
        this.carRepository = carRepository;
        this.auditService = auditService;
    }

    @Override
    public CarDAO findById(UUID carId) {
        return carRepository.getById(carId).orElse(null);
    }

    @Override
    public CarDAO findByIdOrThrow(UUID carId) {
        return carRepository.getById(carId)
                .orElseThrow(() -> new NotFoundException(String.format("Car entity with id = %s not found", carId)));
    }

    @Override
    public List<CarDAO> findAll() {
        return carRepository.getAll();
    }

    @Override
    public Page<CarDAO> findAll(Pageable pageable, CarFilter filter) {
        List<Predicate<CarDAO>> filters = new ArrayList<>();

        if (filter.getModel() != null)
            filters.add(elem -> elem.getModel().equalsIgnoreCase(filter.getModel()));
        if (filter.getReleaseYear() != null)
            filters.add(elem -> elem.getReleaseYear().equals(filter.getReleaseYear()));
        if (filter.getEngineType() != null)
            filters.add(elem -> elem.getEngine().getType().equals(filter.getEngineType()));
        if (filter.getTransmissionType() != null)
            filters.add(elem -> elem.getTransmission().getType().equals(filter.getTransmissionType()));
        if (filter.getDriveType() != null)
            filters.add(elem -> elem.getDrive().equals(filter.getDriveType()));

        return carRepository.getAll(pageable, filters);
    }

    @Override
    @Auditable
    public CarDAO save(CarDAO carDAO) {
        CarDAO car;
        if (carDAO.getCarId() == null) {
            carDAO.setState(CarState.AVAILABLE);
            car = carRepository.save(carDAO);
            auditService.sendEvent(car, AuditAction.CREATE);
        } else {
            carDAO.setState(findByIdOrThrow(carDAO.getCarId()).getState());
            car = carRepository.update(carDAO);
            auditService.sendEvent(car, AuditAction.UPDATE);
        }
        return car;
    }

    @Override
    @Auditable
    public void delete(UUID carId) {
        CarDAO car = findByIdOrThrow(carId);
        auditService.sendEvent(car, AuditAction.DELETE);
        carRepository.delete(carId);
    }

    @Override
    @Auditable
    public void reserve(UUID carId) {
        CarDAO carDAO = findByIdOrThrow(carId);
        carDAO.setState(CarState.RESERVED);
        carRepository.update(carDAO);
        auditService.sendEvent(carDAO, AuditAction.UPDATE);
    }

    @Override
    @Auditable
    public void cancelReserve(UUID carId) {
        CarDAO carDAO = findByIdOrThrow(carId);
        carDAO.setState(CarState.AVAILABLE);
        carRepository.update(carDAO);
        auditService.sendEvent(carDAO, AuditAction.UPDATE);
    }

    @Override
    public Boolean isReserved(UUID carId) {
        CarDAO carDAO = findByIdOrThrow(carId);
        return carDAO.getState() == CarState.RESERVED;
    }

}
