package com.kdk.sberuniversity.api.services.person;

import com.kdk.sberuniversity.api.models.person.PersonListResponse;
import com.kdk.sberuniversity.api.models.person.PersonSaveRequest;
import com.kdk.sberuniversity.api.models.person.PersonSaveResponse;

import java.util.UUID;

public interface PersonService {

    PersonListResponse getList();
    PersonListResponse getByNameStartsWith(String name);
    PersonSaveResponse save(PersonSaveRequest request);
    void delete(UUID personId);

}
