package com.kdk.sberuniversity.api.mappers.car;

import com.kdk.sberuniversity.api.constants.carcontroller.CarAllowedQueryParams;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarDriveType;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarEngineType;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarTransmissionType;
import com.kdk.sberuniversity.persistence.services.car.dto.CarFilter;

import java.util.Map;

public final class CarQueryMapperImpl implements CarQueryMapper {

    public CarFilter mapToCarFilter(Map<String, String> query) {
        String model = query.get(CarAllowedQueryParams.FIND_BY_MODEL.getQueryParam());
        Integer releaseYear = query.containsKey(CarAllowedQueryParams.FIND_BY_RELEASE_YEAR.getQueryParam())
                ? Integer.parseInt(query.get(CarAllowedQueryParams.FIND_BY_RELEASE_YEAR.getQueryParam()))
                : null;
        CarEngineType engineType = query.containsKey(CarAllowedQueryParams.FIND_BY_ENGINE_TYPE.getQueryParam())
                ? CarEngineType.valueOf(query.get(CarAllowedQueryParams.FIND_BY_ENGINE_TYPE.getQueryParam()))
                : null;
        CarTransmissionType transmissionType = query.containsKey(CarAllowedQueryParams.FIND_BY_TRANSMISSION_TYPE.getQueryParam())
                ? CarTransmissionType.valueOf(query.get(CarAllowedQueryParams.FIND_BY_TRANSMISSION_TYPE.getQueryParam()))
                : null;
        CarDriveType driveType = query.containsKey(CarAllowedQueryParams.FIND_BY_DRIVE_TYPE.getQueryParam())
                ? CarDriveType.valueOf(query.get(CarAllowedQueryParams.FIND_BY_DRIVE_TYPE.getQueryParam()))
                : null;

        return new CarFilter(model, releaseYear, engineType, transmissionType, driveType);
    }

}