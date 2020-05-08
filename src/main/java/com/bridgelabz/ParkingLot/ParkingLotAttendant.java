package com.bridgelabz.ParkingLot;


import java.util.*;

public class ParkingLotAttendant {
    public static ArrayList<ParkingLot> parkingLots = new ArrayList<>();
    ParkingLotSystem parkingLot = new ParkingLotSystem(10) ;

    public ParkingLotAttendant(ParkingLot... parkingLot) {

        for (ParkingLot c:parkingLot)
        {
            parkingLots.add(c);
        }
    }
    public int[] parkVehicle(Vehicle vehicle, VehicleDetails vehicleDetails) throws ParkingLotException {
        int [] vehicleArray = parkingLot.park(vehicle, vehicleDetails);
        return vehicleArray;
    }


    public int[] findVehicle(Vehicle vehicle, VehicleDetails vehicleDetails) throws ParkingLotException {
        int[] vehicles = parkingLot.park(vehicle, vehicleDetails);
        return  vehicles;
    }

    public int[] unparkVehicle(Vehicle vehicle) throws ParkingLotException {
        int [] vehicleArray = parkingLot.unPark(vehicle);
        return vehicleArray;
    }
}