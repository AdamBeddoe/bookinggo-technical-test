package com.adambeddoe.bookinggoapplication.pojos;

public class Quote {

    private String supplier;

    private CarType carType;

    private Integer price;

    public Quote() {

    }

    public Quote(String supplier, CarType carType, Integer price) {
        this.supplier = supplier;
        this.carType = carType;
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
        return "{" + this.supplier + "} - {" + this.carType + "} - {" + Integer.toString(this.price) + "}";
    }
}
