package com.kdk.sberuniversity.api.mappers.person;

import com.kdk.sberuniversity.api.models.person.Person;
import com.kdk.sberuniversity.api.models.person.PersonSaveResponse;
import com.kdk.sberuniversity.persistence.repository.person.dao.PersonDAO;

import java.util.List;
import java.util.stream.Collectors;

public final class PersonMapperImpl implements PersonMapper {

    public List<Person> mapToPersonList(List<PersonDAO> persons) {
        return persons.stream()
                .map(this::mapToPersonSaveResponse)
                .collect(Collectors.toList());
    }

    public PersonSaveResponse mapToPersonSaveResponse(PersonDAO personDAO) {
        return new PersonSaveResponse(
                personDAO.getPersonId(),
                personDAO.getFirstName(),
                personDAO.getDateOfBirth(),
                personDAO.getGender()
        );
    }

    public PersonDAO mapToPersonDAO(Person person) {
        return new PersonDAO(person.getId(), person.getFirstName(), person.getDateOfBirth(), person.getGender());
    }

    public Person mapToPerson(PersonDAO personDAO) {
        return new Person(
                personDAO.getPersonId(),
                personDAO.getFirstName(),
                personDAO.getDateOfBirth(),
                personDAO.getGender()
        );
    }

}
