package com.bridgelabz.ParkingLot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLot {

    private  int actualCapacity;
    public List<ParkingSlot> parkingSlotsList;

    public ParkingLot(int capacity) {
        this.parkingSlotsList = new ArrayList<>();
        this.actualCapacity = capacity;
        for (int i=1; i<=actualCapacity; i++){
            parkingSlotsList.add(new ParkingSlot(i, null, null));
        }
    }

    public boolean parkVehicleInToSlots(Vehicle vehicle, VehicleDetails details) throws ParkingLotException {
        Integer parkingSlots = details.parkingType.getParkingSlots(parkingSlotsList);
        if (this.isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle Already Parked", ParkingLotException.ExceptionType.VEHICLE_PARKED_ALREADY);
        if (parkingSlots==0)
            throw new ParkingLotException("Parking Lot is Full", ParkingLotException.ExceptionType.PARKINGLOT_FULL);
        parkingSlotsList.get(parkingSlots - 1).setVehicle(vehicle);
        parkingSlotsList.get(parkingSlots - 1).setTime(LocalDateTime.now());
        return true;

    }

    private boolean isVehicleParked(Vehicle vehicle) {
        if (this.parkingSlotsList.contains(vehicle))
            return true;
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) throws ParkingLotException {
        int parkedVehicle = findVehicle(vehicle);
        parkingSlotsList.get(parkedVehicle - 1).setVehicle(null);
        parkingSlotsList.get(parkedVehicle - 1).setTime(null);
        return true;
    }

    public int findVehicle(Vehicle vehicle) throws ParkingLotException {
        ParkingSlot parkingSlot = this.parkingSlotsList.stream().
                filter(parkingSlotsList -> vehicle.equals(parkingSlotsList.getVehicle())).
                findFirst().orElseThrow(() -> new ParkingLotException("VEHICLE_NOT_FOUND", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND));
        return parkingSlot.getSlotNum();

    }

    public List<Integer> getParkedVehiclesSlotNumbers(VehicleProperties vehicleProperties) {
        if (vehicleProperties.equals(vehicleProperties.color))
            return this.getLocationByColor(this.parkingSlotsList, vehicleProperties);
        else if (vehicleProperties.equals(vehicleProperties.model))
            return  this.getLocationByModel(vehicleProperties);
        else
            return this.getLocation(this.parkingSlotsList, vehicleProperties);
    }

    public List<Integer> getLocationByColor(List<ParkingSlot> parkingSlotsList, VehicleProperties vehicleProperties) {
        return parkingSlotsList.stream().
                filter(parkingSlot -> parkingSlot.getVehicle() != null &&
                        parkingSlot.getVehicle().getColor().equals(vehicleProperties.color)).
                map(ParkingSlot::getSlotNum).
                collect(Collectors.toCollection(ArrayList::new));
    }


    public List<Integer> getLocationByModel( VehicleProperties vehicleProperties) {
        return parkingSlotsList.stream().
                filter(parkingSlot -> parkingSlot.getVehicle() != null &&
                        parkingSlot.getVehicle().getModel().equals(vehicleProperties.model)).
                map(ParkingSlot::getSlotNum).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Integer> getLocation(List<ParkingSlot> parkingSlotsList, VehicleProperties vehicleProperties) {
        return parkingSlotsList.stream().
                filter(parkingSlot -> parkingSlot.getVehicle() != null &&
                        parkingSlot.getVehicle().getColor().equals(vehicleProperties.color) &&
                        parkingSlot.getVehicle().getModel().equals(vehicleProperties.model)).
                map(ParkingSlot::getSlotNum).
                collect(Collectors.toCollection(ArrayList::new));
    }

  /*  public ArrayList<Vehicle> getParkedVehiclesInLast30Min() {
        return parkingSlotsList.stream().
                filter(parkingSlot -> parkingSlot.getVehicle() != null &&
                       parkingSlot.getTime().getMinute() - LocalDateTime.now().getMinute() <= 30).
                map(ParkingSlot::getVehicle).
                collect(Collectors.toCollection(ArrayList::new));
    }*/
}
