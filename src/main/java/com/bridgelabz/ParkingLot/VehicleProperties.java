package com.bridgelabz.ParkingLot;

public class VehicleProperties {

    public Color color;

    public enum Color{
        WHITE, BLUE, BLACK, RED, GREEN
    }

    public VehicleProperties(Color color) {
        this.color = color;
    }
}
