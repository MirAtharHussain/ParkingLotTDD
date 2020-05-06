package com.bridgelabz.ParkingLot;

public class ParkingLotOwner implements  ParkingLotObserver {

    private boolean isFullCapacity;
    ParkingLotAttendant parkingLotAttendant = null;

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

    public boolean parkingLotAttendant(Vehicle vehicle) throws ParkingLotException {
            ParkingLot parkingLot1 = new ParkingLot(5);
            ParkingLot parkingLot2 = new ParkingLot(5);
        parkingLotAttendant = new ParkingLotAttendant(parkingLot1, parkingLot2);
        return parkingLotAttendant.parkVehicle(vehicle);

    }

}
