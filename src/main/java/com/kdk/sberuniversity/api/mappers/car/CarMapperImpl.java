package com.kdk.sberuniversity.api.mappers.car;

import com.kdk.sberuniversity.api.models.car.*;
import com.kdk.sberuniversity.api.models.car.Body;
import com.kdk.sberuniversity.api.models.car.Engine;
import com.kdk.sberuniversity.api.models.car.Salon;
import com.kdk.sberuniversity.api.models.car.Transmission;
import com.kdk.sberuniversity.persistence.repository.car.dao.*;

import java.util.List;
import java.util.stream.Collectors;

public final class CarMapperImpl implements CarMapper {

    public List<Car> mapToCarList(List<CarDAO> cars) {
        return cars.stream()
                .map(this::mapToCarSaveResponse)
                .collect(Collectors.toList());
    }

    public CarSaveResponse mapToCarSaveResponse(CarDAO carDAO) {
        return new CarSaveResponse(
                carDAO.getCarId(),
                carDAO.getBrand(),
                carDAO.getModel(),
                carDAO.getReleaseYear(),
                carDAO.getDateOfReceiptIntoStock(),
                new Body(carDAO.getBody().getType(), carDAO.getBody().getColor()),
                new Engine(carDAO.getEngine().getType(), carDAO.getEngine().getCapacity(), carDAO.getEngine().getPower(), carDAO.getEngine().getTorque()),
                new Transmission(carDAO.getTransmission().getType(), carDAO.getTransmission().getNumberOfGears()),
                carDAO.getDrive(),
                new Salon(carDAO.getSalon().getColor(), carDAO.getSalon().getType()),
                carDAO.getState()
        );
    }

    public CarDAO mapToCarDAO(Car car) {
        return new CarDAO(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getReleaseYear(),
                car.getDateOfReceiptIntoStock(),
                new BodyDAO(car.getBody().getType(), car.getBody().getColor()),
                new EngineDAO(car.getEngine().getType(), car.getEngine().getCapacity(), car.getEngine().getPower(), car.getEngine().getTorque()),
                new TransmissionDAO(car.getTransmission().getType(), car.getTransmission().getNumberOfGears()),
                car.getDrive(),
                new SalonDAO(car.getSalon().getColor(), car.getSalon().getType()),
                car.getState()
        );
    }

    public Car mapToCar(CarDAO carDAO) {
        return new Car(
                carDAO.getCarId(),
                carDAO.getBrand(),
                carDAO.getModel(),
                carDAO.getReleaseYear(),
                carDAO.getDateOfReceiptIntoStock(),
                new Body(carDAO.getBody().getType(), carDAO.getBody().getColor()),
                new Engine(carDAO.getEngine().getType(), carDAO.getEngine().getCapacity(), carDAO.getEngine().getPower(), carDAO.getEngine().getTorque()),
                new Transmission(carDAO.getTransmission().getType(), carDAO.getTransmission().getNumberOfGears()),
                carDAO.getDrive(),
                new Salon(carDAO.getSalon().getColor(), carDAO.getSalon().getType()),
                carDAO.getState()
        );
    }

}
