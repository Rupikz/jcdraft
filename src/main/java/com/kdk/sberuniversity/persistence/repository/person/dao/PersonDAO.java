package com.kdk.sberuniversity.persistence.repository.person.dao;

import java.time.LocalDate;
import java.util.UUID;

public final class PersonDAO {

    private Integer id;
    private UUID personId;
    private String firstName;
    private LocalDate dateOfBirth;
    private Gender gender;

    public PersonDAO(PersonDAO personDAO) {
        this(personDAO.getId(), personDAO.getPersonId(), personDAO.getFirstName(), personDAO.getDateOfBirth(), personDAO.getGender());
    }

    public PersonDAO(Integer id, UUID personId, String firstName, LocalDate dateOfBirth, Gender gender) {
        this.id = id;
        this.personId = personId;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public PersonDAO(UUID personId, String firstName, LocalDate dateOfBirth, Gender gender) {
        this.personId = personId;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
