package com.bridgelabz.ParkingLot.Observer;


public class ParkingLotOwner implements  ParkingLotObserver {
    private boolean isFullCapacity;

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }

    @Override
    public void capacityIsFull() {
        this.isFullCapacity = true;
    }

    @Override
    public void capacityIsAvailable() {
        this.isFullCapacity = false;
    }

}
