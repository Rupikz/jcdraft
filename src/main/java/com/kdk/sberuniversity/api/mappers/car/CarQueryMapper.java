package com.kdk.sberuniversity.api.mappers.car;

import com.kdk.sberuniversity.persistence.services.car.dto.CarFilter;

import java.util.Map;

public interface CarQueryMapper {

    CarFilter mapToCarFilter(Map<String, String> query);

}
