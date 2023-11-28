package com.kdk.sberuniversity.host;

import com.kdk.sberuniversity.api.constants.AllowedMethods;
import com.kdk.sberuniversity.api.controller.ControllersFactory;
import com.kdk.sberuniversity.api.services.ResponseSenderServiceImpl;
import com.kdk.sberuniversity.core.actions.ControllerScope;
import com.kdk.sberuniversity.core.constants.ControllerScopeElement;
import com.kdk.sberuniversity.core.di.DIContainer;
import com.kdk.sberuniversity.core.routers.PathSelectionService;
import com.kdk.sberuniversity.core.routers.PathSelectionServiceImpl;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.AbstractMap;
import java.util.Map;

public final class AppContext {

    private final Map<String, ControllerScope> routes;

    public AppContext() {
        routes = Map.ofEntries(
                // Person
                new AbstractMap.SimpleEntry<>("GET/api/v1/person/" + AllowedMethods.LIST.getMethodName(),
                        new ControllerScope(ControllerScopeElement.PERSON)),
                new AbstractMap.SimpleEntry<>("GET/api/v1/person/" + AllowedMethods.FIND_BY_CRITERIA.getMethodName(),
                        new ControllerScope(ControllerScopeElement.PERSON)),
                new AbstractMap.SimpleEntry<>("PUT/api/v1/person/" + AllowedMethods.SAVE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.PERSON)),
                new AbstractMap.SimpleEntry<>("DELETE/api/v1/person/" + AllowedMethods.DELETE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.PERSON)),

                // Car
                new AbstractMap.SimpleEntry<>("GET/api/v1/car/" + AllowedMethods.LIST.getMethodName(),
                        new ControllerScope(ControllerScopeElement.CAR)),
                new AbstractMap.SimpleEntry<>("GET/api/v1/car/" + AllowedMethods.PAGE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.CAR)),
                new AbstractMap.SimpleEntry<>("GET/api/v1/car/" + AllowedMethods.FIND_BY_CRITERIA.getMethodName(),
                        new ControllerScope(ControllerScopeElement.CAR)),
                new AbstractMap.SimpleEntry<>("PUT/api/v1/car/" + AllowedMethods.SAVE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.CAR)),
                new AbstractMap.SimpleEntry<>("DELETE/api/v1/car/" + AllowedMethods.DELETE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.CAR)),
                new AbstractMap.SimpleEntry<>("PUT/api/v1/car/" + AllowedMethods.RESERVE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.CAR)),
                new AbstractMap.SimpleEntry<>("PUT/api/v1/car/" + AllowedMethods.CANCEL_RESERVE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.CAR)),
                new AbstractMap.SimpleEntry<>("GET/api/v1/car/" + AllowedMethods.IS_RESERVED.getMethodName(),
                        new ControllerScope(ControllerScopeElement.CAR)),

                // Order
                new AbstractMap.SimpleEntry<>("GET/api/v1/order/" + AllowedMethods.LIST.getMethodName(),
                        new ControllerScope(ControllerScopeElement.ORDER)),
                new AbstractMap.SimpleEntry<>("GET/api/v1/order/" + AllowedMethods.PAGE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.ORDER)),
                new AbstractMap.SimpleEntry<>("GET/api/v1/order/" + AllowedMethods.FIND_BY_CRITERIA.getMethodName(),
                        new ControllerScope(ControllerScopeElement.ORDER)),
                new AbstractMap.SimpleEntry<>("PUT/api/v1/order/" + AllowedMethods.SAVE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.ORDER)),
                new AbstractMap.SimpleEntry<>("DELETE/api/v1/order/" + AllowedMethods.DELETE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.ORDER))
        );
    }

    public void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        PathSelectionService router = new PathSelectionServiceImpl(routes);
        server.createContext("/", new HttpHandlerMediator(
                new ControllersFactory(
                        router,
                        DIContainer.getPersonController(),
                        DIContainer.getCarController(),
                        DIContainer.getOrderController(),
                        DIContainer.getDeadEndController()),
                new ResponseSenderServiceImpl()));
        server.setExecutor(null);
        server.start();
    }

}
