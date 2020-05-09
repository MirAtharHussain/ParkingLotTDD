package com.bridgelabz.ParkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLot {

    private  int actualCapacity;
    public List<ParkingSlot> parkingSlotsList;

    public ParkingLot(int capacity) {
        this.parkingSlotsList = new ArrayList<>();
        this.actualCapacity = capacity;
        for (int i=1; i<=actualCapacity; i++){
            parkingSlotsList.add(new ParkingSlot(i, null));
        }
    }

    public boolean parkVehicleInToSlots(Vehicle vehicle) throws ParkingLotException {
        Integer parkingSlots = this.getParkingSlots(parkingSlotsList);
        if (this.parkingSlotsList.contains(vehicle))
            throw new ParkingLotException("Vehicle Already Parked", ParkingLotException.ExceptionType.VEHICLE_PARKED_ALREADY);
        if (parkingSlots==0)
            throw new ParkingLotException("Parking Lot is Full", ParkingLotException.ExceptionType.PARKINGLOT_FULL);
        parkingSlotsList.get(parkingSlots - 1).setVehicle(vehicle);
        return true;

    }

    public boolean unparkVehicle(Vehicle vehicle) throws ParkingLotException {
        int parkedVehicle = findVehicle(vehicle);
        parkingSlotsList.get(parkedVehicle - 1).setVehicle(null);
        return true;
    }

    public int findVehicle(Vehicle vehicle) throws ParkingLotException {
        ParkingSlot parkingSlot = this.parkingSlotsList.stream().
                filter(parkingSlotsList -> vehicle.equals(parkingSlotsList.getVehicle())).
                findFirst().orElseThrow(() -> new ParkingLotException("VEHICLE_NOT_FOUND", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND));
        return parkingSlot.getSlotNum();

    }
    private Integer getParkingSlots(List<ParkingSlot> parkingSlotsList) {
        return (IntStream.range(0, parkingSlotsList.size()).
                filter(i -> parkingSlotsList.get(i).getVehicle()==null).findFirst().orElse(-1))+1;
    }
}
