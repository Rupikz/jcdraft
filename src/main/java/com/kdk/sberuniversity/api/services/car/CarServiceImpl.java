package com.kdk.sberuniversity.api.services.car;

import com.kdk.sberuniversity.api.mappers.car.CarMapper;
import com.kdk.sberuniversity.api.models.PageResponse;
import com.kdk.sberuniversity.api.models.car.Car;
import com.kdk.sberuniversity.api.models.car.CarListResponse;
import com.kdk.sberuniversity.api.models.car.CarSaveRequest;
import com.kdk.sberuniversity.api.models.car.CarSaveResponse;
import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.core.exceptions.http.BadRequestException;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarDAO;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarState;
import com.kdk.sberuniversity.persistence.services.car.CarRepositoryService;
import com.kdk.sberuniversity.persistence.services.car.dto.CarFilter;

import java.util.List;
import java.util.UUID;

public final class CarServiceImpl implements CarService {

    private final CarRepositoryService carRepositoryService;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepositoryService carRepositoryService, CarMapper carMapper) {
        this.carRepositoryService = carRepositoryService;
        this.carMapper = carMapper;
    }

    @Override
    public Car getOneOrThrow(UUID carId) {
        CarDAO carDAO = carRepositoryService.findByIdOrThrow(carId);
        return carMapper.mapToCar(carDAO);
    }

    @Override
    public CarListResponse getList() {
        List<CarDAO> carsDAO = carRepositoryService.findAll();
        return new CarListResponse(carMapper.mapToCarList(carsDAO));
    }

    @Override
    public PageResponse<Car> getPage(Pageable pageable, CarFilter filter) {
        Page<CarDAO> pageOfCarDAO = carRepositoryService.findAll(pageable, filter);
        return new PageResponse<>(
                pageOfCarDAO.getOffset(),
                pageOfCarDAO.getSize(),
                pageOfCarDAO.hasNext(),
                carMapper.mapToCarList(pageOfCarDAO.getContent())
        );
    }

    @Override
    public CarSaveResponse save(CarSaveRequest request) {
        CarDAO savedCar = carRepositoryService.save(carMapper.mapToCarDAO(request));
        return carMapper.mapToCarSaveResponse(savedCar);
    }

    @Override
    public void delete(UUID carId) {
        carRepositoryService.delete(carId);
    }

    @Override
    public void reserve(UUID carId) {
        CarDAO carDAO = carRepositoryService.findByIdOrThrow(carId);
        if (carDAO.getState() == CarState.RESERVED) {
            throw new BadRequestException("Car already has been reserved");
        }
        carRepositoryService.reserve(carId);
    }

    @Override
    public void cancelReserve(UUID carId) {
        carRepositoryService.cancelReserve(carId);
    }

    @Override
    public Boolean isReserved(UUID carId) {
        return carRepositoryService.isReserved(carId);
    }

}
