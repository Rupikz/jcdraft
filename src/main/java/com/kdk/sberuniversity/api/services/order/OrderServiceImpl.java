package com.kdk.sberuniversity.api.services.order;

import com.kdk.sberuniversity.api.mappers.order.OrderMapper;
import com.kdk.sberuniversity.api.models.PageResponse;
import com.kdk.sberuniversity.api.models.order.Order;
import com.kdk.sberuniversity.api.models.order.OrderListResponse;
import com.kdk.sberuniversity.api.models.order.OrderSaveRequest;
import com.kdk.sberuniversity.api.models.order.OrderSaveResponse;
import com.kdk.sberuniversity.api.pagination.Page;
import com.kdk.sberuniversity.api.pagination.Pageable;
import com.kdk.sberuniversity.core.exceptions.http.BadRequestException;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarState;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderDAO;
import com.kdk.sberuniversity.persistence.services.car.CarRepositoryService;
import com.kdk.sberuniversity.persistence.services.order.OrderRepositoryService;
import com.kdk.sberuniversity.persistence.services.order.dto.OrderFilter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class OrderServiceImpl implements OrderService {

    private final OrderRepositoryService orderRepositoryService;
    private final CarRepositoryService carRepositoryService;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepositoryService orderRepositoryService,
                            CarRepositoryService carRepositoryService, OrderMapper orderMapper) {
        this.orderRepositoryService = orderRepositoryService;
        this.carRepositoryService = carRepositoryService;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order getOneOrThrow(UUID orderId) {
        OrderDAO orderDAO = orderRepositoryService.findByIdOrThrow(orderId);
        return orderMapper.mapToOrder(orderDAO);
    }

    @Override
    public OrderListResponse getList() {
        List<OrderDAO> ordersDAO = orderRepositoryService.findAll();
        return new OrderListResponse(orderMapper.mapToOrderList(ordersDAO));
    }

    @Override
    public PageResponse<Order> getPage(Pageable pageable, OrderFilter filter) {
        Page<OrderDAO> pageOfOrderDAO = orderRepositoryService.findAll(pageable, filter);
        return new PageResponse<>(
                pageOfOrderDAO.getOffset(),
                pageOfOrderDAO.getSize(),
                pageOfOrderDAO.hasNext(),
                orderMapper.mapToOrderList(pageOfOrderDAO.getContent()));
    }

    @Override
    public OrderSaveResponse save(OrderSaveRequest request) {
        if (request.getCarsId() != null) {
            List<String> notFoundCarsId = request.getCarsId().stream()
                    .map(carId -> carRepositoryService.findById(carId) != null ? null : carId)
                    .filter(Objects::nonNull)
                    .map(UUID::toString)
                    .toList();
            if (!notFoundCarsId.isEmpty()) {
                throw new NotFoundException("Car with ids: " + String.join(", ", notFoundCarsId) + " not found");
            }

            List<String> reservedCarsId = request.getCarsId().stream()
                    .map(carId -> carRepositoryService.findById(carId).getState() == CarState.RESERVED ? carId : null)
                    .filter(Objects::nonNull)
                    .map(UUID::toString)
                    .toList();
            if (!reservedCarsId.isEmpty()) {
                throw new BadRequestException("Car with ids: " + String.join(", ", reservedCarsId) + " already reserved");
            }
        }

        OrderDAO savedOrder = orderRepositoryService.save(orderMapper.mapToOrderDAO(request));
        return orderMapper.mapToOrderSaveResponse(savedOrder);
    }

    @Override
    public void delete(UUID orderId) {
        orderRepositoryService.delete(orderId);
    }

}
