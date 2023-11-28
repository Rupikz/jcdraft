package com.kdk.sberuniversity.core.actions;

import com.kdk.sberuniversity.core.constants.ControllerScopeElement;

public final class ControllerScope {

    private final ControllerScopeElement controllerScopeElement;

    public ControllerScope(ControllerScopeElement controllerScopeElement) {
        this.controllerScopeElement = controllerScopeElement;
    }

    public ControllerScopeElement getController() {
        return controllerScopeElement;
    }

}
