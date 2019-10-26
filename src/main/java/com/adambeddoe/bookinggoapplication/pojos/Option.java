package com.adambeddoe.bookinggoapplication.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Option {

    @JsonProperty("car_type")
    private CarType carType;

    private Integer price;

    public Option() {

    }

    public Option(CarType carType, Integer price) {
        this.carType = carType;
        this.price = price;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" + this.carType + "} - {" + Integer.toString(this.price) + "}";
    }
}
