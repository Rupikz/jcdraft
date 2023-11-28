package com.kdk.sberuniversity.persistence.services.person;

import com.kdk.sberuniversity.persistence.repository.person.dao.PersonDAO;

import java.util.List;
import java.util.UUID;

public interface PersonRepositoryService {

    PersonDAO findById(UUID personId);
    PersonDAO findByIdOrThrow(UUID personId);
    List<PersonDAO> findAll();
    List<PersonDAO> findByNameStartsWith(String name);
    PersonDAO save(PersonDAO personDAO);
    void delete(UUID personId);

}
