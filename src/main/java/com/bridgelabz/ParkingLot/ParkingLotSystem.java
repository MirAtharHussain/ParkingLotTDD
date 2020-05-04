package com.bridgelabz.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    private  List<ParkingLotObserver> observers;
    private List vehicleList;
    private  int actualCapacity;
    private ParkingLotOwner owner;


    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicleList = new ArrayList<>();
        this.actualCapacity = capacity;
    }
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void  setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }


    public void park(Object vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle Already Parked");
        if (this.vehicleList.size()  == this.actualCapacity) {
           for (ParkingLotObserver observer: observers){
               observer.capacityIsFull();
           }
            throw new ParkingLotException("Parking Lot is Full");
        }
        this.vehicleList.add(vehicle);
    }

    public boolean unPark(Object vehicle)  {
        if (vehicle == null) return false;
        if (this.vehicleList.contains(vehicle)){
            this.vehicleList.remove(vehicle);
            for (ParkingLotObserver observer: observers){
                observer.capacityIsAvailable();
            }
            return true;
        }
        return false;
    }
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicleList.contains(vehicle))
            return true;
        return false;
    }


    public boolean findMyVehicle(Vehicle vehicle) throws ParkingLotException {
        if (!vehicleList.contains(vehicle))
            throw new ParkingLotException("VEHICLE_NOT_FOUND");
        return true;
    }
}
