package com.kdk.sberuniversity.api.controller;

import com.kdk.sberuniversity.api.constants.AllowedMethods;
import com.kdk.sberuniversity.api.constants.carcontroller.CarAllowedQueryParams;
import com.kdk.sberuniversity.api.mappers.PageableQueryMapper;
import com.kdk.sberuniversity.api.mappers.car.CarQueryMapper;
import com.kdk.sberuniversity.api.models.PageResponse;
import com.kdk.sberuniversity.api.models.car.*;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.api.services.MarshallingService;
import com.kdk.sberuniversity.api.services.car.CarService;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;
import com.kdk.sberuniversity.core.exceptions.http.BadRequestException;
import com.kdk.sberuniversity.persistence.services.car.dto.CarFilter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.kdk.sberuniversity.core.constants.ExceptionConstants.QUERY_PARAMS_NOT_FOUND;

public final class CarController extends Controller<CarAllowedQueryParams> {

    private final CarService carService;
    private final CarQueryMapper carQueryMapper;
    private final PageableQueryMapper pageableQueryMapper;

    public CarController(Map<String, Map<AllowedMethods, List<CarAllowedQueryParams>>> supportedRoutes,
                         CarService carService, CarQueryMapper carQueryMapper,
                         PageableQueryMapper pageableQueryMapper) {
        super(supportedRoutes);
        this.carService = carService;
        this.carQueryMapper = carQueryMapper;
        this.pageableQueryMapper = pageableQueryMapper;
    }

    @Override
    public CarListResponse findAll(HttpExchangeAdapter adapter) {
        return carService.getList();
    }

    @Override
    public PageResponse<Car> findPage(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();

        Pageable pageable = pageableQueryMapper.mapToPageRequest(query);
        CarFilter filter = carQueryMapper.mapToCarFilter(query);

        return carService.getPage(pageable, filter);
    }

    @Override
    public Car findByCriteria(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(CarAllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        UUID carId = UUID.fromString(query.get(CarAllowedQueryParams.FIND_BY_ID.getQueryParam()));
        return carService.getOneOrThrow(carId);
    }

    @Override
    public CarSaveResponse save(HttpExchangeAdapter adapter) {
        String requestBody = getRequestBody(adapter);
        if (requestBody.isEmpty()) {
            throw new BadRequestException("Request body is empty");
        }

        CarSaveRequest saveRequest = MarshallingService.unmarshall(requestBody, Car.class, CarSaveRequest.class);
        return carService.save(saveRequest);
    }

    @Override
    public void delete(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(CarAllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        String carIdValue = query.get(CarAllowedQueryParams.FIND_BY_ID.getQueryParam());
        carService.delete(UUID.fromString(carIdValue));
    }

    @Override
    public void reserve(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(CarAllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        String carIdValue = query.get(CarAllowedQueryParams.FIND_BY_ID.getQueryParam());
        carService.reserve(UUID.fromString(carIdValue));
    }

    @Override
    public void cancelReserve(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(CarAllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        String carIdValue = query.get(CarAllowedQueryParams.FIND_BY_ID.getQueryParam());
        carService.cancelReserve(UUID.fromString(carIdValue));
    }

    @Override
    public CarReservedStatusResponse isReserved(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(CarAllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        String carIdValue = query.get(CarAllowedQueryParams.FIND_BY_ID.getQueryParam());
        Boolean reservedStatus = carService.isReserved(UUID.fromString(carIdValue));
        return new CarReservedStatusResponse(reservedStatus);
    }

}