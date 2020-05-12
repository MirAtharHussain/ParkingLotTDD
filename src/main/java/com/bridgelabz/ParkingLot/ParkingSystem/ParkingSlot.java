package com.bridgelabz.ParkingLot.ParkingSystem;

import com.bridgelabz.ParkingLot.VehicleInformation.Vehicle;

import java.time.LocalDateTime;

public class ParkingSlot {
    private LocalDateTime time;
    private int slotNum;
    private Vehicle vehicle;

    public ParkingSlot(int slotNum, LocalDateTime time, Vehicle vehicle) {
        this.slotNum = slotNum;
        this.vehicle = vehicle;
        this.time = time;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
