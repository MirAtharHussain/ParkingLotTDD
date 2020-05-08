package com.bridgelabz.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public boolean parkingLotAttendant(Object vehicle) {
        parkingLotAttendant = new ParkingLotAttendant();
        return parkingLotAttendant.parkVehicle(vehicle);

    }
}
