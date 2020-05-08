package com.bridgelabz.ParkingLot;

public class VehicleDetails {
    private  VehicleAttributes.Driver driverType;
    private  VehicleAttributes.Color color;
    private  VehicleAttributes.Size size;
    private  VehicleAttributes.Model model;

    public VehicleDetails(VehicleAttributes.Driver driver, VehicleAttributes.Color color, VehicleAttributes.Size size, VehicleAttributes.Model model) {
        this.driverType = driver;
        this.color = color;
        this.size = size;
        this.model = model;
    }
}
