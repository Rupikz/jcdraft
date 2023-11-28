package com.kdk.sberuniversity.api.controller;

import com.kdk.sberuniversity.core.actions.ControllerScope;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;
import com.kdk.sberuniversity.core.routers.PathSelectionService;

public final class ControllersFactory {

    private final PathSelectionService pathSelectionService;
    private final PersonController personController;
    private final CarController carController;
    private final OrderController orderController;
    private final DeadEndController deadEndController;

    public ControllersFactory(PathSelectionService pathSelectionService,
                              PersonController personController,
                              CarController carController,
                              OrderController orderController,
                              DeadEndController deadEndController) {
        this.pathSelectionService = pathSelectionService;
        this.personController = personController;
        this.carController = carController;
        this.orderController = orderController;
        this.deadEndController = deadEndController;
    }

    public Controller<?> get(HttpExchangeAdapter adapter) {
        return pathSelectionService
                .get(adapter)
                .map(this::findController)
                .orElse(deadEndController);
    }

    public Controller findController(ControllerScope controllerScope) {
        return switch (controllerScope.getController()) {
            case PERSON -> personController;
            case CAR -> carController;
            case ORDER -> orderController;
        };
    }

}
