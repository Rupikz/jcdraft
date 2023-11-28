package com.kdk.sberuniversity.api.models.car;

import com.kdk.sberuniversity.persistence.repository.car.dao.CarBodyType;
import com.kdk.sberuniversity.persistence.repository.car.dao.Color;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "body", propOrder = {"type", "color"})
@XmlRootElement(name = "body")
public final class Body {

    private CarBodyType type;
    private Color color;

    public Body(CarBodyType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Body() {
    }

    public CarBodyType getType() {
        return type;
    }

    public void setType(CarBodyType type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
