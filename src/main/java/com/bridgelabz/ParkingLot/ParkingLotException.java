package com.bridgelabz.ParkingLot;

public class ParkingLotException extends Exception {
    enum ExceptionType{
        PARKINGLOT_FULL, VEHICLE_NOT_FOUND, VEHICLE_PARKED_ALREADY
    }

    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
