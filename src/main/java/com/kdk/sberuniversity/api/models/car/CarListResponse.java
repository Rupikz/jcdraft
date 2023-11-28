package com.kdk.sberuniversity.api.models.car;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cars", propOrder = {
        "cars"
})
@XmlRootElement(name = "cars")
public final class CarListResponse {

    @XmlElement(name = "car")
    private List<Car> cars;

    public CarListResponse(List<Car> cars) {
        this.cars = cars;
    }

    public CarListResponse() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
