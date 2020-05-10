package com.bridgelabz.ParkingLot;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotSystem {

    private  List<ParkingLotObserver> observers;
    private List<ParkingLot> parkingLotList;
    private boolean isParkingFull;
    private int actualCapacity;
    private VehicleDetails details;


    public ParkingLotSystem(ParkingLot...parkingLots) {
        this.observers = new ArrayList<>();
        this.parkingLotList = new ArrayList<>();
        this.parkingLotList.addAll(Arrays.asList(parkingLots));
    }

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }
    public  void setCapacity(int capacity){
        actualCapacity = capacity;
    }

    public ParkingLot getLot(VehicleDetails details) throws ParkingLotException {
        ParkingLot parkingLot = details.parkingType.getParkingLot(parkingLotList);
       if( parkingLot.parkingSlotsList.stream().filter(parkingSlot -> parkingSlot.getVehicle()==null).count()==0) {
           this.isParkingFull = true;
           throw new ParkingLotException("Parking Lot is Full", ParkingLotException.ExceptionType.PARKINGLOT_FULL);

        }
       this.isParkingFull = false;
       return  parkingLot;
    }

    public boolean park(Vehicle vehicle, VehicleDetails details) throws ParkingLotException {
            this.observers.forEach((observer)-> {observer.capacityIsFull();});
            return this.getLot(details).parkVehicleInToSlots(vehicle, details);
    }

    public boolean unPark(Vehicle vehicle, VehicleDetails vehicleDetails) throws ParkingLotException {
        this.observers.forEach((observer)-> {observer.capacityIsAvailable();});
        return this.getLot(vehicleDetails).unparkVehicle(vehicle);

    }

    public ParkingLot findMyVehicle(Vehicle vehicle) throws ParkingLotException {
        return this.parkingLotList.stream().
                filter(parkingLot -> parkingLot.parkingSlotsList.stream().map(ParkingSlot::getVehicle).anyMatch(vehicle1 -> vehicle1 == vehicle)).
                findFirst().orElseThrow(()-> new ParkingLotException("VEHICLE_NOT_FOUND", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND));
    }

    public ArrayList<List<Integer>> getLocation(VehicleProperties vehicleProperties) {
        return parkingLotList.stream().map(parkingLot -> parkingLot.getParkedVehiclesSlotNumbers(vehicleProperties)).
                collect(Collectors.toCollection(ArrayList::new));

    }
}