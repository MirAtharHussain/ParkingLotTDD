package com.bridgelabz.ParkingLot.VehicleInformation;

import com.bridgelabz.ParkingLot.Exception.Row;

public class Vehicle {

    private  String attendantName;
    private VehicleProperties.Model model;
    public VehicleProperties.Color color;
    private String plateNumber;
    public Row row;

    public Vehicle(String plateNumber,String attendantName, VehicleProperties.Color color, VehicleProperties.Model model) {
        this.plateNumber = plateNumber;
        this.attendantName = attendantName;
        this.color = color;
        this.model = model;
    }

    public VehicleProperties.Color getColor() {
        return color;
    }

    public VehicleProperties.Model getModel() {
        return model;
    }

    public String getAttendantName() {
        return attendantName;
    }
}
