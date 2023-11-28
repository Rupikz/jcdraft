package com.kdk.sberuniversity.api.controller;

import com.kdk.sberuniversity.api.constants.AllowedMethods;
import com.kdk.sberuniversity.api.constants.personcontroller.PersonAllowedQueryParams;
import com.kdk.sberuniversity.api.models.person.Person;
import com.kdk.sberuniversity.api.models.person.PersonListResponse;
import com.kdk.sberuniversity.api.models.person.PersonSaveRequest;
import com.kdk.sberuniversity.api.models.person.PersonSaveResponse;
import com.kdk.sberuniversity.api.services.MarshallingService;
import com.kdk.sberuniversity.api.services.person.PersonService;
import com.kdk.sberuniversity.core.adapters.HttpExchangeAdapter;
import com.kdk.sberuniversity.core.exceptions.http.BadRequestException;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.kdk.sberuniversity.core.constants.ExceptionConstants.ENDPOINT_DOES_NOT_EXIST;
import static com.kdk.sberuniversity.core.constants.ExceptionConstants.QUERY_PARAMS_NOT_FOUND;

public final class PersonController extends Controller<PersonAllowedQueryParams> {

    private final PersonService personService;

    public PersonController(Map<String, Map<AllowedMethods, List<PersonAllowedQueryParams>>> supportedRoutes,
                            PersonService personService) {
        super(supportedRoutes);
        this.personService = personService;
    }

    @Override
    public PersonListResponse findAll(HttpExchangeAdapter adapter) {
        return personService.getList();
    }

    @Override
    public PersonListResponse findByCriteria(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(PersonAllowedQueryParams.FIND_BY_FIRST_NAME.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        String firstName = query.get(PersonAllowedQueryParams.FIND_BY_FIRST_NAME.getQueryParam());
        return personService.getByNameStartsWith(firstName);
    }

    @Override
    public PersonSaveResponse save(HttpExchangeAdapter adapter) {
        String requestBody = getRequestBody(adapter);
        if (requestBody.isEmpty()) {
            throw new BadRequestException("Request body is empty");
        }

        PersonSaveRequest saveRequest = MarshallingService.unmarshall(requestBody, Person.class, PersonSaveRequest.class);
        return personService.save(saveRequest);
    }

    @Override
    public void delete(HttpExchangeAdapter adapter) {
        Map<String, String> query = adapter.extractQuery();
        if (!query.containsKey(PersonAllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            throw new BadRequestException(QUERY_PARAMS_NOT_FOUND);
        }

        String personIdValue = query.get(PersonAllowedQueryParams.FIND_BY_ID.getQueryParam());
        personService.delete(UUID.fromString(personIdValue));
    }

    @Override
    public Object findPage(HttpExchangeAdapter adapter) {
        throw new NotFoundException(ENDPOINT_DOES_NOT_EXIST);
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