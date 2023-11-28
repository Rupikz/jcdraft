package com.kdk.sberuniversity.api.constants.personcontroller;

import com.kdk.sberuniversity.api.constants.AllowedMethods;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface PersonControllerRoutes {

    Map<String, Map<AllowedMethods, List<PersonAllowedQueryParams>>> SUPPORTED_ROUTES = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("GET", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.LIST, Collections.emptyList()),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.FIND_BY_CRITERIA, List.of(PersonAllowedQueryParams.FIND_BY_ID, PersonAllowedQueryParams.FIND_BY_FIRST_NAME))
            )),
            new AbstractMap.SimpleEntry<>("PUT", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.SAVE, Collections.emptyList())
            )),
            new AbstractMap.SimpleEntry<>("DELETE", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.DELETE, List.of(PersonAllowedQueryParams.FIND_BY_ID))
            ))
    );

}
