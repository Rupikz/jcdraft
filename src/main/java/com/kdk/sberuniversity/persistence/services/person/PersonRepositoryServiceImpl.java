package com.kdk.sberuniversity.persistence.services.person;

import com.kdk.sberuniversity.audit.AuditAction;
import com.kdk.sberuniversity.audit.annotation.Auditable;
import com.kdk.sberuniversity.audit.services.AuditService;
import com.kdk.sberuniversity.core.exceptions.http.NotFoundException;
import com.kdk.sberuniversity.persistence.repository.person.PersonRepository;
import com.kdk.sberuniversity.persistence.repository.person.dao.PersonDAO;

import java.util.List;
import java.util.UUID;

public final class PersonRepositoryServiceImpl implements PersonRepositoryService {

    private final PersonRepository personRepository;
    private final AuditService auditService;

    public PersonRepositoryServiceImpl(PersonRepository personRepository, AuditService auditService) {
        this.personRepository = personRepository;
        this.auditService = auditService;
    }

    @Override
    public PersonDAO findById(UUID personId) {
        return personRepository.getById(personId).orElse(null);
    }

    @Override
    public PersonDAO findByIdOrThrow(UUID personId) {
        return personRepository.getById(personId)
                .orElseThrow(() -> new NotFoundException(String.format("Person entity with id = %s not found", personId)));
    }

    @Override
    public List<PersonDAO> findAll() {
        return personRepository.getAll();
    }

    @Override
    public List<PersonDAO> findByNameStartsWith(String name) {
        return personRepository.findByNameStartsWith(name);
    }

    @Override
    @Auditable
    public PersonDAO save(PersonDAO personDAO) {
        PersonDAO person;
        if (personDAO.getPersonId() == null) {
            person = personRepository.save(personDAO);
            auditService.sendEvent(person, AuditAction.CREATE);
        } else {
            person = personRepository.update(personDAO);
            auditService.sendEvent(person, AuditAction.UPDATE);
        }
        return person;
    }

    @Override
    @Auditable
    public void delete(UUID personId) {
        PersonDAO person = findByIdOrThrow(personId);
        auditService.sendEvent(person, AuditAction.DELETE);
        personRepository.delete(personId);
    }

}
