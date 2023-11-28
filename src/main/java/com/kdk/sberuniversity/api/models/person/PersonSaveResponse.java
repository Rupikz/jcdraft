package com.kdk.sberuniversity.api.models.person;

import com.kdk.sberuniversity.persistence.repository.person.dao.Gender;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.UUID;

@XmlRootElement(name = "person")
public final class PersonSaveResponse extends Person {

    public PersonSaveResponse(UUID id, String firstName, LocalDate dateOfBirth, Gender gender) {
        super(id, firstName, dateOfBirth, gender);
    }

    public PersonSaveResponse(String firstName, LocalDate dateOfBirth, Gender gender) {
        super(firstName, dateOfBirth, gender);
    }

    // Парамлесс CTOR нужен для корректной работы маршаллера
    public PersonSaveResponse() {
    }

}
