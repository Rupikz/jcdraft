package com.kdk.sberuniversity.api.models.person;

import com.kdk.sberuniversity.api.adapters.LocalDateAdapter;
import com.kdk.sberuniversity.persistence.repository.person.dao.Gender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = {
        "id", "firstName", "dateOfBirth", "gender"
})
@XmlRootElement(name = "person")
public class Person {

    private UUID id;
    private String firstName;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOfBirth;
    private Gender gender;

    public Person(UUID id, String firstName, LocalDate dateOfBirth, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Person(String firstName, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Person() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
