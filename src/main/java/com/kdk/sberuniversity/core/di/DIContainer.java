package com.kdk.sberuniversity.core.di;

import com.kdk.sberuniversity.api.constants.carcontroller.CarControllerRoutes;
import com.kdk.sberuniversity.api.constants.ordercontroller.OrderControllerRoutes;
import com.kdk.sberuniversity.api.constants.personcontroller.PersonControllerRoutes;
import com.kdk.sberuniversity.api.controller.CarController;
import com.kdk.sberuniversity.api.controller.DeadEndController;
import com.kdk.sberuniversity.api.controller.OrderController;
import com.kdk.sberuniversity.api.controller.PersonController;
import com.kdk.sberuniversity.api.mappers.PageableQueryMapper;
import com.kdk.sberuniversity.api.mappers.PageableQueryMapperImpl;
import com.kdk.sberuniversity.api.mappers.car.CarMapper;
import com.kdk.sberuniversity.api.mappers.car.CarMapperImpl;
import com.kdk.sberuniversity.api.mappers.car.CarQueryMapper;
import com.kdk.sberuniversity.api.mappers.car.CarQueryMapperImpl;
import com.kdk.sberuniversity.api.mappers.order.OrderMapper;
import com.kdk.sberuniversity.api.mappers.order.OrderMapperImpl;
import com.kdk.sberuniversity.api.mappers.order.OrderQueryMapper;
import com.kdk.sberuniversity.api.mappers.order.OrderQueryMapperImpl;
import com.kdk.sberuniversity.api.mappers.person.PersonMapper;
import com.kdk.sberuniversity.api.mappers.person.PersonMapperImpl;
import com.kdk.sberuniversity.api.services.car.CarService;
import com.kdk.sberuniversity.api.services.car.CarServiceImpl;
import com.kdk.sberuniversity.api.services.order.OrderService;
import com.kdk.sberuniversity.api.services.order.OrderServiceImpl;
import com.kdk.sberuniversity.api.services.person.PersonService;
import com.kdk.sberuniversity.api.services.person.PersonServiceImpl;
import com.kdk.sberuniversity.audit.services.AuditService;
import com.kdk.sberuniversity.audit.services.AuditServiceImpl;
import com.kdk.sberuniversity.persistence.repository.car.CarRepository;
import com.kdk.sberuniversity.persistence.repository.car.CarRepositoryImpl;
import com.kdk.sberuniversity.persistence.repository.order.OrderRepository;
import com.kdk.sberuniversity.persistence.repository.order.OrderRepositoryImpl;
import com.kdk.sberuniversity.persistence.repository.person.PersonRepository;
import com.kdk.sberuniversity.persistence.repository.person.PersonRepositoryImpl;
import com.kdk.sberuniversity.persistence.services.car.CarRepositoryService;
import com.kdk.sberuniversity.persistence.services.car.CarRepositoryServiceImpl;
import com.kdk.sberuniversity.persistence.services.order.OrderRepositoryService;
import com.kdk.sberuniversity.persistence.services.order.OrderRepositoryServiceImpl;
import com.kdk.sberuniversity.persistence.services.person.PersonRepositoryService;
import com.kdk.sberuniversity.persistence.services.person.PersonRepositoryServiceImpl;

import java.util.Collections;

public class DIContainer {
    // DeadEnd
    private static DeadEndController deadEndController;

    // Person
    private static PersonController personController;
    private static PersonRepository personRepository;
    private static PersonRepositoryService personRepositoryService;
    private static PersonMapper personMapper;
    private static PersonService personService;

    // Car
    private static CarController carController;
    private static CarRepository carRepository;
    private static CarRepositoryService carRepositoryService;
    private static CarMapper carMapper;
    private static CarQueryMapper carQueryMapper;
    private static CarService carService;

    // Order
    private static OrderController orderController;
    private static OrderRepository orderRepository;
    private static OrderRepositoryService orderRepositoryService;
    private static OrderMapper orderMapper;
    private static OrderQueryMapper orderQueryMapper;
    private static OrderService orderService;

    // Misc
    private static AuditService auditService;
    private static PageableQueryMapper pageableQueryMapper;

    // DeadEnd dependencies
    public static DeadEndController getDeadEndController() {
        if (deadEndController == null)
            deadEndController = new DeadEndController(Collections.emptyMap());
        return deadEndController;
    }

    // Person dependencies
    public static PersonController getPersonController() {
        if (personController == null)
            personController = new PersonController(PersonControllerRoutes.SUPPORTED_ROUTES, getPersonService());
        return personController;
    }

    private static PersonRepository getPersonRepository() {
        if (personRepository == null)
            personRepository = new PersonRepositoryImpl();
        return personRepository;
    }

    private static PersonRepositoryService getPersonRepositoryService() {
        if (personRepositoryService == null)
            personRepositoryService = new PersonRepositoryServiceImpl(getPersonRepository(), getAuditService());
        return personRepositoryService;
    }

    private static PersonMapper getPersonMapper() {
        if (personMapper == null)
            personMapper = new PersonMapperImpl();
        return personMapper;
    }

    private static PersonService getPersonService() {
        if (personService == null)
            personService = new PersonServiceImpl(getPersonRepositoryService(), getPersonMapper());
        return personService;
    }

    // Car dependencies
    public static CarController getCarController() {
        if (carController == null)
            carController = new CarController(CarControllerRoutes.SUPPORTED_ROUTES, getCarService(), getCarFilterMapper(), getPageableQueryMapper());
        return carController;
    }

    private static CarRepository getCarRepository() {
        if (carRepository == null)
            carRepository = new CarRepositoryImpl();
        return carRepository;
    }

    private static CarRepositoryService getCarRepositoryService() {
        if (carRepositoryService == null)
            carRepositoryService = new CarRepositoryServiceImpl(getCarRepository(), getAuditService());
        return carRepositoryService;
    }

    private static CarMapper getCarMapper() {
        if (carMapper == null)
            carMapper = new CarMapperImpl();
        return carMapper;
    }

    private static CarQueryMapper getCarFilterMapper() {
        if (carQueryMapper == null)
            carQueryMapper = new CarQueryMapperImpl();
        return carQueryMapper;
    }

    private static CarService getCarService() {
        if (carService == null)
            carService = new CarServiceImpl(getCarRepositoryService(), getCarMapper());
        return carService;
    }

    // Order dependencies
    public static OrderController getOrderController() {
        if (orderController == null)
            orderController = new OrderController(OrderControllerRoutes.SUPPORTED_ROUTES, getOrderService(), getOrderFilterMapper(), getPageableQueryMapper());
        return orderController;
    }

    private static OrderRepository getOrderRepository() {
        if (orderRepository == null)
            orderRepository = new OrderRepositoryImpl();
        return orderRepository;
    }

    private static OrderRepositoryService getOrderRepositoryService() {
        if (orderRepositoryService == null)
            orderRepositoryService = new OrderRepositoryServiceImpl(getOrderRepository(), getAuditService());
        return orderRepositoryService;
    }

    private static OrderMapper getOrderMapper() {
        if (orderMapper == null)
            orderMapper = new OrderMapperImpl();
        return orderMapper;
    }

    private static OrderQueryMapper getOrderFilterMapper() {
        if (orderQueryMapper == null)
            orderQueryMapper = new OrderQueryMapperImpl();
        return orderQueryMapper;
    }

    private static OrderService getOrderService() {
        if (orderService == null)
            orderService = new OrderServiceImpl(getOrderRepositoryService(), getCarRepositoryService(), getOrderMapper());
        return orderService;
    }

    // Misc dependencies
    private static AuditService getAuditService() {
        if (auditService == null)
            auditService = new AuditServiceImpl(getCarMapper(), getPersonMapper(), getOrderMapper());
        return auditService;
    }

    private static PageableQueryMapper getPageableQueryMapper() {
        if (pageableQueryMapper == null)
            pageableQueryMapper = new PageableQueryMapperImpl();
        return pageableQueryMapper;
    }

}
