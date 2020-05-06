package com.bridgelabz.ParkingLot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ParkingLotAttendant {

    private final ParkingLotSystem parkingLotSystem;
    private final ParkingLot parkingLot1;
    private final ParkingLot parkingLot2;

    public ParkingLotAttendant(ParkingLot parkingLot1, ParkingLot parkingLot2) {
        this.parkingLotSystem = new ParkingLotSystem(10);
        this.parkingLot1 = parkingLot1;
        this.parkingLot2 = parkingLot2;
    }

    public boolean parkVehicle(Vehicle vehicle) throws ParkingLotException {
        boolean results = true;
        if (parkingLot1.getSize() == parkingLot2.getSize() && (parkingLot1.getCapacity()>=parkingLot1.getSize()) && parkingLot2.getCapacity()>=parkingLot2.getSize()) {
            parkingLot1.setSize(parkingLot1.getSize() + 1);
            parkingLotSystem.park(vehicle);
        }else if (parkingLot1.getSize()>parkingLot2.getSize() && parkingLot2.getCapacity()>=parkingLot2.getSize()){
            parkingLot2.setSize(parkingLot2.getSize() + 1);
            parkingLotSystem.park(vehicle);
        }else if (parkingLot1.getSize()<parkingLot2.getSize() && (parkingLot1.getCapacity()>=parkingLot1.getSize())) {
            parkingLot1.setSize(parkingLot1.getSize() + 1);
            parkingLotSystem.park(vehicle);
        }else{
            results = false;
        }
        System.out.println(parkingLot1.getSize()+" "+parkingLot2.getSize());
        return results;
    }
  /*  public boolean whereVehicleParked(Vehicle vehicle){
        return parkingLotSystem.isVehicleParked(vehicle);
    }*/

}
