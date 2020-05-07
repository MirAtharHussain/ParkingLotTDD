package com.bridgelabz.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotOwner implements  ParkingLotObserver {
    private boolean isFullCapacity;
    ParkingLotAttendant parkingLotAttendant = null;
    public static Map<Vehicle, ArrayList<Object>> listOfParkedCar = new HashMap<>();

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


    public String getCarParkedTime(Vehicle vehicle)  {
        ArrayList<Object> time = listOfParkedCar.get(vehicle);
        return time.get(4).toString();
    }

    public int[] parkingLotAttendant(Vehicle vehicle, ArrayList<Object> details) throws ParkingLotException {
        return parkingLotAttendant.parkVehicle(vehicle, details);
    }

}
