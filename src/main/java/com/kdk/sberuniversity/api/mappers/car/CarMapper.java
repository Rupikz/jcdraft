package com.kdk.sberuniversity.api.mappers.car;

import com.kdk.sberuniversity.api.models.car.Car;
import com.kdk.sberuniversity.api.models.car.CarSaveResponse;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarDAO;

import java.util.List;

public interface CarMapper {

    CarSaveResponse mapToCarSaveResponse(CarDAO carDAO);
    List<Car> mapToCarList(List<CarDAO> cars);
    Car mapToCar(CarDAO carDAO);
    CarDAO mapToCarDAO(Car car);

}
