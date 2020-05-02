package com.bridgelabz.ParkingLot;

public class ParkingLotOwner {
    private boolean isFullCapacity;

    public void capacityIsFull(){
        isFullCapacity = true;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
