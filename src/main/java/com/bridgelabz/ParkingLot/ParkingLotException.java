package com.bridgelabz.ParkingLot;

public class ParkingLotException extends Exception {

    enum ExceptionType{
        PARKINGLOT_FULL, NO_VEHICLE_FOUND, VEHICLE_NOT_FOUND, VEHICLE_ALREADY_PARKED

    }

         ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public ParkingLotException(String message) {
        super(message);
    }
}
