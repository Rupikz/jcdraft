package com.kdk.sberuniversity.persistence.repository.person;

import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.persistence.repository.person.dao.Gender;
import com.kdk.sberuniversity.persistence.repository.person.dao.PersonDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class PersonRepositoryImpl implements PersonRepository {

    private static int ID_SEQUENCE;
    private static final List<PersonDAO> PERSONS_REPOSITORY;

    static {
        PERSONS_REPOSITORY = new ArrayList<>(List.of(
                new PersonDAO(getId(), UUID.randomUUID(), "Petr", LocalDate.of(1989, 12, 4), Gender.MALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Olga", LocalDate.of(1992, 3, 15), Gender.FEMALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Svetlana", LocalDate.of(1991, 2, 20), Gender.FEMALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Igor", LocalDate.of(1988, 7, 4), Gender.MALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Stanislav", LocalDate.of(1980, 3, 15), Gender.MALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Oksana", LocalDate.of(1989, 4, 2), Gender.FEMALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Pavel", LocalDate.of(1994, 12, 12), Gender.MALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Pier", LocalDate.of(1991, 6, 19), Gender.MALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Olesya", LocalDate.of(1995, 9, 21), Gender.FEMALE),
                new PersonDAO(getId(), UUID.randomUUID(), "Kira", LocalDate.of(1992, 10, 23), Gender.FEMALE)
        ));
    }

    private static int getId() {
        return ++ID_SEQUENCE;
    }

    private static int getIdxOf(PersonDAO personDAO) {
        return PERSONS_REPOSITORY.indexOf(
                PERSONS_REPOSITORY
                        .stream()
                        .filter(elem -> elem.getPersonId().equals(personDAO.getPersonId()))
                        .findFirst()
                        .get());
    }

    public List<PersonDAO> getAll() {
        return PERSONS_REPOSITORY;
    }

    public Optional<PersonDAO> getById(UUID personId) {
        return PERSONS_REPOSITORY.stream()
                .filter(elem -> elem.getPersonId().equals(personId))
                .map(PersonDAO::new)
                .findFirst();
    }

    @Override
    public List<PersonDAO> findByNameStartsWith(String name) {
        return getAll().stream()
                .filter(elem -> elem.getFirstName().startsWith(name.toUpperCase()))
                .toList();
    }

    @Override
    public PersonDAO save(PersonDAO personDAO) {
        personDAO.setId(getId());
        personDAO.setPersonId(UUID.randomUUID());
        PERSONS_REPOSITORY.add(personDAO);

        return PERSONS_REPOSITORY.get(PERSONS_REPOSITORY.size() - 1);
    }

    @Override
    public PersonDAO update(PersonDAO personDAO) {
        PersonDAO existingRecord = getById(personDAO.getPersonId())
                .orElseThrow(() -> new NotFoundException(String.format("Person entity with id = %s not found", personDAO.getPersonId())));

        existingRecord.setFirstName(personDAO.getFirstName());
        existingRecord.setDateOfBirth(personDAO.getDateOfBirth());
        existingRecord.setGender(personDAO.getGender());
        PERSONS_REPOSITORY.set(getIdxOf(personDAO), existingRecord);

        return getById(personDAO.getPersonId())
                .orElseThrow(() -> new NotFoundException(String.format("Person entity with id = %s not found", personDAO.getPersonId())));
    }

    @Override
    public void delete(UUID personId) {
        PersonDAO existingRecord = getById(personId)
                .orElseThrow(() -> new NotFoundException(String.format("Person entity with id = %s not found", personId)));
        PERSONS_REPOSITORY.remove(getIdxOf(existingRecord));
    }

}
