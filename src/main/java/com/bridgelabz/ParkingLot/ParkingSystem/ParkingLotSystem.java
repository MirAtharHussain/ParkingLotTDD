package com.bridgelabz.ParkingLot.ParkingSystem;

import com.bridgelabz.ParkingLot.Exception.ParkingLotException;
import com.bridgelabz.ParkingLot.Mockito.ParkingLotMockito;
import com.bridgelabz.ParkingLot.Observer.ParkingLotObserver;
import com.bridgelabz.ParkingLot.VehicleInformation.Vehicle;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleDetails;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleMockito;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleProperties;
import com.bridgelabz.ParkingLot.enums.ParkingType;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotSystem {

    private  ParkingLotMockito parkinLotMockito;
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
    /*-----------------------------------Mockito  Implementation--------------------------------*/

    public ParkingLotSystem(ParkingLotMockito...parkingLots) {
        this.parkinLotMockito = parkingLots[0];
    }
        public void parkVehicle(VehicleMockito vehicle)  {

            this.parkinLotMockito.park(vehicle);

        }

        public boolean isVehicleParked(VehicleMockito vehicle)  {

            return this.parkinLotMockito.isVehicleParked(vehicle);

        }

        public void unParkVehicle(VehicleMockito vehicle) {

            this.parkinLotMockito.unPark(vehicle);

        }

        public int findVehicleSlot(VehicleMockito vehicle) {

            return this.parkinLotMockito.findSlot(vehicle);

        }

        public int findVehicleLotNumber(VehicleMockito vehicle) {

            return this.parkinLotMockito.findLotNumber(vehicle);

        }

    /*-----------------------------------Mockito  Implementation--------------------------------*/


    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
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

    public List getVehiclesParkedInRowBOrD(ParkingType vehicleDetails) throws ParkingLotException {
        return parkingLotList.stream().map(parkingLot -> parkingLot.getVehiclesParkedInRowBOrD(vehicleDetails)).
                collect(Collectors.toCollection(ArrayList::new));
    }
}