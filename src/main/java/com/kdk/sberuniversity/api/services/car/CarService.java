package com.kdk.sberuniversity.api.services.car;

import com.kdk.sberuniversity.api.models.PageResponse;
import com.kdk.sberuniversity.api.models.car.Car;
import com.kdk.sberuniversity.api.models.car.CarListResponse;
import com.kdk.sberuniversity.api.models.car.CarSaveRequest;
import com.kdk.sberuniversity.api.models.car.CarSaveResponse;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.persistence.services.car.dto.CarFilter;

import java.util.UUID;

public interface CarService {

    Car getOneOrThrow(UUID carId);
    CarListResponse getList();
    PageResponse<Car> getPage(Pageable pageable, CarFilter filter);
    CarSaveResponse save(CarSaveRequest request);
    void delete(UUID carId);
    void reserve(UUID carId);
    void cancelReserve(UUID carId);
    Boolean isReserved(UUID carId);

}
