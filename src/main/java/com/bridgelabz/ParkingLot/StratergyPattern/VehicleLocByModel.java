package com.bridgelabz.ParkingLot.StratergyPattern;

import com.bridgelabz.ParkingLot.ParkingSystem.ParkingSlot;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleLocByModel implements VehicleLocation {
    @Override
    public List<Integer> getLocation(List<ParkingSlot> parkingSlotsList, VehicleProperties vehicleProperties) {
        return parkingSlotsList.stream().
                filter(parkingSlot -> parkingSlot.getVehicle() != null &&
                        parkingSlot.getVehicle().getModel().equals(vehicleProperties.model)).
                map(ParkingSlot::getSlotNum).
                collect(Collectors.toCollection(ArrayList::new));
    }
}
