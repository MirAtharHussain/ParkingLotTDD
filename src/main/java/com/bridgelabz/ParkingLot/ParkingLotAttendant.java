package com.bridgelabz.ParkingLot;

public class ParkingLotAttendant {
    ParkingLot parkingLot;

    public boolean parkVehicle(Vehicle vehicle) throws ParkingLotException {
        return parkingLot.parkVehicleInToSlots(vehicle);
    }
}
