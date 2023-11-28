package com.kdk.sberuniversity.api.models.car;

import com.kdk.sberuniversity.persistence.repository.car.dao.CarInteriorUpholsteryType;
import com.kdk.sberuniversity.persistence.repository.car.dao.Color;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salon", propOrder = {"color", "type"})
@XmlRootElement(name = "salon")
public final class Salon {

    private Color color;
    private CarInteriorUpholsteryType type;

    public Salon(Color color, CarInteriorUpholsteryType type) {
        this.color = color;
        this.type = type;
    }

    public Salon() {
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public CarInteriorUpholsteryType getType() {
        return type;
    }

    public void setType(CarInteriorUpholsteryType type) {
        this.type = type;
    }

}