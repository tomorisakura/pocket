package com.enigma.pocket.dto;

public class Engine {

    private String type;
    private String cc;
    private Car car;

    public Engine(String type, String cc) {
        this.type = type;
        this.cc = cc;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
}
