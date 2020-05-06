package com.bridgelabz.ParkingLot;

import java.time.LocalTime;
import java.util.*;

public class ParkingLotSystem {

    private  List<ParkingLotObserver> observers;
    private  int actualCapacity;
    private List vehicleList;
    ParkingLotOwner owner;

    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicleList = new ArrayList();
        this.actualCapacity = capacity;
    }

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void  setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }


    public void park(Vehicle vehicle) throws ParkingLotException {
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

    public boolean unPark(Vehicle vehicle)  {
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
    public boolean isVehicleParked(Vehicle vehicle) {
        if (this.vehicleList.contains(vehicle))
            return true;
        return false;
    }


    public boolean findMyVehicle(Vehicle vehicle) throws ParkingLotException {
        if (!vehicleList.contains(vehicle))
            throw new ParkingLotException("VEHICLE_NOT_FOUND");
        unPark(vehicle);
        return true;
    }
}
