package com.kdk.sberuniversity.api.services.person;

import com.kdk.sberuniversity.api.mappers.person.PersonMapper;
import com.kdk.sberuniversity.api.models.person.PersonListResponse;
import com.kdk.sberuniversity.api.models.person.PersonSaveRequest;
import com.kdk.sberuniversity.api.models.person.PersonSaveResponse;
import com.kdk.sberuniversity.persistence.repository.person.dao.PersonDAO;
import com.kdk.sberuniversity.persistence.services.person.PersonRepositoryService;

import java.util.List;
import java.util.UUID;

public final class PersonServiceImpl implements PersonService {

    private final PersonRepositoryService personRepositoryService;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepositoryService personRepositoryService, PersonMapper personMapper) {
        this.personRepositoryService = personRepositoryService;
        this.personMapper = personMapper;
    }

    @Override
    public PersonListResponse getList() {
        List<PersonDAO> personsDAO = personRepositoryService.findAll();
        return new PersonListResponse(personMapper.mapToPersonList(personsDAO));
    }

    @Override
    public PersonListResponse getByNameStartsWith(String name) {
        List<PersonDAO> personsDAO = personRepositoryService.findByNameStartsWith(name);
        return new PersonListResponse(personMapper.mapToPersonList(personsDAO));
    }

    @Override
    public PersonSaveResponse save(PersonSaveRequest request) {
        PersonDAO savedPerson = personRepositoryService.save(personMapper.mapToPersonDAO(request));
        return personMapper.mapToPersonSaveResponse(savedPerson);
    }

    @Override
    public void delete(UUID personId) {
        personRepositoryService.delete(personId);
    }

}
