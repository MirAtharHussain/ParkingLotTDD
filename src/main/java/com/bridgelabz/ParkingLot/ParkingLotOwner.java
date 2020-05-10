package com.bridgelabz.ParkingLot;


public class ParkingLotOwner implements  ParkingLotObserver {
    private boolean isFullCapacity;
    ParkingLotAttendant parkingLotAttendant = null;

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

   /* public boolean parkingLotAttendant(Vehicle vehicle) throws ParkingLotException {
        parkingLotAttendant = new ParkingLotAttendant();
        return parkingLotAttendant.parkVehicle(vehicle);

    }*/
}
