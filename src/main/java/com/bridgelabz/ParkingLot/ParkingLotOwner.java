package com.bridgelabz.ParkingLot;

public class ParkingLotOwner implements  ParkingLotObserver {
    private boolean isFullCapacity;
    PrakingLotAttendant prakingLotAttendant = null;

    @Override
    public void capacityIsFull(){
        isFullCapacity = true;
    }

    @Override
    public void capacityIsAvailable() {
        isFullCapacity = false;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }

    public boolean parkingLotAttendant(Object vehicle) {
        prakingLotAttendant = new PrakingLotAttendant();
        return prakingLotAttendant.parkVehicle(vehicle);

    }
}
