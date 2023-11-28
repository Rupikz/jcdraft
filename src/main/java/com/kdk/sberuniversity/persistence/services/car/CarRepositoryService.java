package com.kdk.sberuniversity.persistence.services.car;

import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarDAO;
import com.kdk.sberuniversity.persistence.services.car.dto.CarFilter;

import java.util.List;
import java.util.UUID;

public interface CarRepositoryService {

    CarDAO findById(UUID carId);
    CarDAO findByIdOrThrow(UUID carId);
    List<CarDAO> findAll();
    Page<CarDAO> findAll(Pageable pageable, CarFilter filter);
    CarDAO save(CarDAO carDAO);
    void delete(UUID carId);
    void reserve(UUID carId);
    void cancelReserve(UUID carId);
    Boolean isReserved(UUID carId);

}
