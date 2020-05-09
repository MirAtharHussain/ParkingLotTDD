package com.bridgelabz.ParkingLot;

import java.util.Objects;

public class ParkingSlot {
    private int slotNum;
    private Vehicle vehicle;

    public ParkingSlot(int slotNum, Vehicle vehicle) {
        this.slotNum = slotNum;
        this.vehicle = vehicle;
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
}
