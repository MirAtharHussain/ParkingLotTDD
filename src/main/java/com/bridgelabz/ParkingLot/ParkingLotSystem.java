package com.bridgelabz.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    private List vehicles;
    private  int actualCapacity;
    private ParkingLotOwner owner;


    public ParkingLotSystem(int capacity) {
        this.vehicles = new ArrayList<>();
        this.actualCapacity = capacity;
    }
    public void registerOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }

    public void  setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }


    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size()  == this.actualCapacity) {
            owner.capacityIsFull();
            throw new ParkingLotException("Parking Lot is Full");
        }
        this.vehicles.add(vehicle);
    }

    public boolean unPark(Object vehicle)  {
        if (vehicle == null) return false;
        if (this.vehicles.contains(vehicle)){
            this.vehicles.remove(vehicle);
            return true;
        }
        return false;
    }
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }
}
