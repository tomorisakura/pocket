package com.enigma.pocket.dto;

public class Car {
    private String name;
    private String color;
    private Engine engine;

    public Car(String name, String color, Engine engine) {
        this.name = name;
        this.color = color;
        //engine.setCar(this);
        this.engine = engine;
    }

    public void printSuperEngineSound(EngineX engineX) {
        engineX.supperSound("Vrooom");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
