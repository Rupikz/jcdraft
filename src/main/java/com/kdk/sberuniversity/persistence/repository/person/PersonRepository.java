package com.kdk.sberuniversity.persistence.repository.person;

import com.kdk.sberuniversity.persistence.repository.person.dao.PersonDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    List<PersonDAO> getAll();
    Optional<PersonDAO> getById(UUID personId);
    List<PersonDAO> findByNameStartsWith(String name);
    PersonDAO save(PersonDAO personDAO);
    PersonDAO update(PersonDAO personDAO);
    void delete(UUID personId);

}
