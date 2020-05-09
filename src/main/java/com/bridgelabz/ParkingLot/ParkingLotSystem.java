package com.bridgelabz.ParkingLot;

import java.util.*;

public class ParkingLotSystem {

    private  List<ParkingLotObserver> observers;
    private List<ParkingLot> parkingLotList;
    private boolean isParkingFull;
    private int actualCapacity;


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

    public ParkingLot getLot() throws ParkingLotException {
        ParkingLot parkingLot = this.getParkingLot(parkingLotList);
       if( parkingLot.parkingSlotsList.stream().filter(parkingSlot -> parkingSlot.getVehicle()==null).count()==0) {
           this.isParkingFull = true;
           throw new ParkingLotException("Parking Lot is Full", ParkingLotException.ExceptionType.PARKINGLOT_FULL);

        }
       this.isParkingFull = false;
       return  parkingLot;
    }

    public boolean park(Vehicle vehicle) throws ParkingLotException {
            this.observers.forEach((observer)-> {observer.capacityIsFull();});
            return this.getLot().parkVehicleInToSlots(vehicle);
    }

    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        this.observers.forEach((observer)-> {observer.capacityIsAvailable();});
        return this.getLot().unparkVehicle(vehicle);

    }

    public ParkingLot findMyVehicle(Vehicle vehicle) throws ParkingLotException {
        return this.parkingLotList.stream().
                filter(parkingLot -> parkingLot.parkingSlotsList.stream().map(ParkingSlot::getVehicle).anyMatch(vehicle1 -> vehicle1 == vehicle)).
                findFirst().orElseThrow(()-> new ParkingLotException("VEHICLE_NOT_FOUND", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND));
    }

    public ParkingLot getParkingLot(List<ParkingLot> parkingLotList){
        ArrayList<ParkingLot> sortList = new ArrayList<>();
        sortList.addAll(parkingLotList);
        sortList.sort(Comparator.comparing(parkingLot ->{
                long count = parkingLot.parkingSlotsList.stream().
                        filter(parkingSlot -> parkingSlot.getVehicle() == null).count();
                return  -count;
            }));
        return sortList.get(0);
    }
}