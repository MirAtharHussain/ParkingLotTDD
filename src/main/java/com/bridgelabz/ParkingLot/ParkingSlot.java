package com.bridgelabz.ParkingLot;

import java.time.LocalDateTime;

public class ParkingSlot {
    private  LocalDateTime time;
    private int slotNum;
    private Vehicle vehicle;

    public ParkingSlot(int slotNum, LocalDateTime time, Vehicle vehicle) {
        this.slotNum = slotNum;
        this.vehicle = vehicle;
        this.time =time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return slotNum == that.slotNum &&
                vehicle.equals(that.vehicle);
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
