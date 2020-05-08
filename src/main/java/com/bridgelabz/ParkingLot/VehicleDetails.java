package com.bridgelabz.ParkingLot;

public class VehicleDetails {
    private  VEhicleAttributes.Driver driverType;
    private  VEhicleAttributes.Color color;
    private  VEhicleAttributes.Size size;
    private  VEhicleAttributes.Model model;

    public VehicleDetails(VEhicleAttributes.Driver driver, VEhicleAttributes.Color color, VEhicleAttributes.Size size, VEhicleAttributes.Model model) {
        this.driverType = driver;
        this.color = color;
        this.size = size;
        this.model = model;
    }
}
