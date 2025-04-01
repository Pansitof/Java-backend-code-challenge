package com.practices.resultpattern;

public class Coche {
    private String plate;
    private double fuel;
    private boolean functional;

    public Coche(String plate, double fuel, boolean functional) {
        this.plate = plate;
        this.fuel = fuel;
        this.functional = functional;
    }

    public boolean tryMotor(){
        return functional && fuel > 0;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public boolean isFunctional() {
        return functional;
    }

    public void setFunctional(boolean functional) {
        this.functional = functional;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "plate='" + plate + '\'' +
                ", fuel=" + fuel +
                ", functional=" + functional +
                '}';
    }
}
