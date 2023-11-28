package com.kdk.sberuniversity.api.mappers.person;

import com.kdk.sberuniversity.api.models.person.Person;
import com.kdk.sberuniversity.api.models.person.PersonSaveResponse;
import com.kdk.sberuniversity.persistence.repository.person.dao.PersonDAO;

import java.util.List;

public interface PersonMapper {

    PersonSaveResponse mapToPersonSaveResponse(PersonDAO personDAO);
    List<Person> mapToPersonList(List<PersonDAO> persons);
    Person mapToPerson(PersonDAO carDAO);
    PersonDAO mapToPersonDAO(Person person);

}
