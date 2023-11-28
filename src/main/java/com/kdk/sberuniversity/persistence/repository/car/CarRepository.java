package com.kdk.sberuniversity.persistence.repository.car;

import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface CarRepository {

    List<CarDAO> getAll();
    Page<CarDAO> getAll(Pageable pageable, List<Predicate<CarDAO>> filters);
    Optional<CarDAO> getById(UUID carId);
    CarDAO save(CarDAO carDAO);
    CarDAO update(CarDAO carDAO);
    void delete(UUID carId);

}
