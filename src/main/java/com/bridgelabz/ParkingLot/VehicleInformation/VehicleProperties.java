package com.bridgelabz.ParkingLot.VehicleInformation;

public class VehicleProperties {

    public Model model;
    public Color color;

    public enum Color{
        WHITE, BLUE, BLACK, RED, GREEN
    }
    public enum Model{
        TOYOTA, BMW, MERCEDES
    }

    public VehicleProperties(Color color, Model model) {
        this.color = color;
        this.model = model;
    }
    public VehicleProperties(Color color) {
        this.color = color;
    }
    public VehicleProperties(Model model) {
        this.model = model;
    }
}
