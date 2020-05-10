package com.bridgelabz.ParkingLot;

public class Vehicle {

    public VehicleProperties.Color color;
    private String plateNumber;

    public Vehicle(String plateNumber, VehicleProperties.Color color) {
        this.plateNumber = plateNumber;
        this.color = color;
    }

    public VehicleProperties.Color getColor() {
        return color;
    }
}
