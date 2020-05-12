package com.bridgelabz.ParkingLot.StratergyPattern;

import com.bridgelabz.ParkingLot.ParkingSystem.ParkingSlot;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleProperties;

import java.util.List;

public interface VehicleLocation {
    public List<Integer> getLocation(List<ParkingSlot> parkingSlotsList, VehicleProperties vehicleProperties);
}
