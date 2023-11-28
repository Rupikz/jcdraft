package com.kdk.sberuniversity.api.controller;

import com.kdk.sberuniversity.api.constants.AllowedMethods;
import com.kdk.sberuniversity.api.constants.ordercontroller.OrderAllowedQueryParams;
import com.kdk.sberuniversity.api.mappers.PageableQueryMapper;
import com.kdk.sberuniversity.api.mappers.order.OrderQueryMapper;
import com.kdk.sberuniversity.api.models.PageResponse;
import com.kdk.sberuniversity.api.models.order.Order;
import com.kdk.sberuniversity.api.models.order.OrderListResponse;
import com.kdk.sberuniversity.api.models.order.OrderSaveRequest;
import com.kdk.sberuniversity.api.models.order.OrderSaveResponse;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.api.services.MarshallingService;
import com.kdk.sberuniversity.api.services.order.OrderService;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;
import com.kdk.sberuniversity.core.exceptions.http.BadRequestException;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.persistence.services.order.dto.OrderFilter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.kdk.sberuniversity.core.constants.ExceptionConstants.ENDPOINT_DOES_NOT_EXIST;
import static com.kdk.sberuniversity.core.constants.ExceptionConstants.QUERY_PARAMS_NOT_FOUND;

public final class OrderController extends Controller<OrderAllowedQueryParams> {

    private final OrderService orderService;
    private final OrderQueryMapper orderQueryMapper;
    private final PageableQueryMapper pageableQueryMapper;

    public OrderController(Map<String, Map<AllowedMethods, List<OrderAllowedQueryParams>>> supportedRoutes,
                           OrderService orderService, OrderQueryMapper orderQueryMapper,
                           PageableQueryMapper pageableQueryMapper) {
        super(supportedRoutes);
        this.orderService = orderService;
        this.orderQueryMapper = orderQueryMapper;
        this.pageableQueryMapper = pageableQueryMapper;
    }

    @Override
    public OrderListResponse findAll(HttpExchangeAdapter adapter) {
        return orderService.getList();
    }

    @Override
    public PageResponse<Order> findPage(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();

        Pageable pageable = pageableQueryMapper.mapToPageRequest(query);
        OrderFilter filter = orderQueryMapper.mapToOrderFilter(query);

        return orderService.getPage(pageable, filter);
    }

    @Override
    public Order findByCriteria(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(OrderAllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        UUID orderId = UUID.fromString(query.get(OrderAllowedQueryParams.FIND_BY_ID.getQueryParam()));
        return orderService.getOneOrThrow(orderId);
    }

    @Override
    public OrderSaveResponse save(HttpExchangeAdapter adapter) {
        String requestBody = getRequestBody(adapter);
        if (requestBody.isEmpty()) {
            throw new BadRequestException("Request body is empty");
        }

        OrderSaveRequest saveRequest = MarshallingService.unmarshall(requestBody, Order.class, OrderSaveRequest.class);
        return orderService.save(saveRequest);
    }

    @Override
    public void delete(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(OrderAllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        String orderIdValue = query.get(OrderAllowedQueryParams.FIND_BY_ID.getQueryParam());
        orderService.delete(UUID.fromString(orderIdValue));
    }

    @Override
    public void reserve(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public void cancelReserve(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

    @Override
    public Object isReserved(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
    }

}