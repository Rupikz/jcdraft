package com.kdk.sberuniversity.core.routers;

import com.kdk.sberuniversity.core.actions.ControllerScope;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;

import java.util.Optional;

public interface PathSelectionService {

    Optional<ControllerScope> get(HttpExchangeAdapter adapter);

}
