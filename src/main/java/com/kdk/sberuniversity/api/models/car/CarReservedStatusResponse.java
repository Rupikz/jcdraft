package com.kdk.sberuniversity.api.models.car;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {"isReserved"})
@XmlRootElement(name = "car")
public final class CarReservedStatusResponse {

    private Boolean isReserved;

    public CarReservedStatusResponse(Boolean isReserved) {
        this.isReserved = isReserved;
    }

    public CarReservedStatusResponse() {
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

}
