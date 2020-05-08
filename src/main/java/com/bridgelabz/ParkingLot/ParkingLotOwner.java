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


    public int[] parkingLotAttendant(Vehicle vehicle, VehicleDetails details) throws ParkingLotException {
        int[] parkVehicle = parkingLotAttendant.parkVehicle(vehicle, details);
        System.out.println(parkVehicle);
        return  parkVehicle;
    }

}
