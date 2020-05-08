package com.bridgelabz.ParkingLot;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ParkingLotSystem {

    private List<ParkingLotObserver> observers;
    private int actualCapacity;
    public Map<Vehicle, ArrayList<Object>> vehicleList;
    List<ParkingLot> parkingLots = ParkingLotAttendant.parkingLots;

    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicleList = new HashMap<>();
        this.actualCapacity = capacity;
    }

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    public int[] park(Vehicle vehicle, ArrayList<Object> details) throws ParkingLotException {
        int arr[] = new int[2];
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle Already Parked",ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED);
        if (vehicleList.size() == actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full", ParkingLotException.ExceptionType.PARKINGLOT_FULL);
        }

        if (!vehicleList.containsKey(vehicle)) {
            LocalDateTime current = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String parkedTime = current.format(format);
            details.add(parkedTime);


            boolean proceed = true;
            for (int i = 0; i < parkingLots.size(); i++) {
                if ((int) parkingLots.get(i).space[0] == 0) {
                    parkingLots.get(i).space[1] = vehicle;
                    parkingLots.get(i).space[0] = 1;
                    proceed = false;
                    arr[0] = i;
                    arr[1] = 1;
                    break;
                }
            }
            if (proceed) {
                int value = Integer.MAX_VALUE;
                int array = 0;
                int index = 0;
                for (int i = 0; i < parkingLots.size(); i++) {
                    Object[] vehicleParkArray = parkingLots.get(i).space;
                    if (value > (int) vehicleParkArray[0]) {
                        value = (int) vehicleParkArray[0];
                        array = i;
                        index = (int) vehicleParkArray[0] + 1;
                    }
                }
                parkingLots.get(array).space[index] = vehicle;
                parkingLots.get(array).space[0] = value + 1;
                arr[0] = array;
                arr[1] = index;
            }

            vehicleList.put(vehicle, details);
            ParkingLotOwner.listOfParkedCar.put(vehicle, details);
        }
        return arr;
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        if (this.vehicleList.containsKey(vehicle))
            return true;
        return false;
    }

    public int[] unPark(Vehicle vehicle) throws ParkingLotException {
        int arr[] = new int[2];
        arr[0] = -1;
        arr[1] = -1;
        if (!vehicleList.containsKey(vehicle))
            throw new ParkingLotException("NO Vehicle Found",ParkingLotException.ExceptionType.NO_VEHICLE_FOUND);
        if (vehicleList.containsKey(vehicle)) {

            for (int i = 0; i < parkingLots.size(); i++) {
                Object[] vehicleParkArray = parkingLots.get(i).space;
                if ((int) vehicleParkArray[0] != 0) {
                    for (int j = 1; j < vehicleParkArray.length; j++) {
                        if (vehicleParkArray[j].equals(vehicle)) {
                            int size = (int) parkingLots.get(i).space[0];
                            parkingLots.get(i).space[size] = 0;
                            parkingLots.get(i).space[0] = size - 1;
                            arr[0] = i;
                            arr[1] = size;
                            vehicleList.remove(vehicle);
                            return arr;
                        }
                    }
                }
            }
        }
        return arr;
    }


    public boolean findMyVehicle(Vehicle vehicle) throws ParkingLotException {
        if (!vehicleList.containsKey(vehicle))
            throw new ParkingLotException("VEHICLE_NOT_FOUND");
        return true;

    }
}