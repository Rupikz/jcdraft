package com.kdk.sberuniversity.api.models.person;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "persons", propOrder = {
        "persons"
})
@XmlRootElement(name = "persons")
public final class PersonListResponse {

    @XmlElement(name = "person")
    private List<Person> persons;

    public PersonListResponse(List<Person> persons) {
        this.persons = persons;
    }

    public PersonListResponse() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}
