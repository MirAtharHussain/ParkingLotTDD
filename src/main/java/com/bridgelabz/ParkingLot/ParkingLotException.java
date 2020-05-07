package com.bridgelabz.ParkingLot;

public class ParkingLotException extends Exception {


    enum  ExceptionType{
        VEHICLE_NOT_FOUND, PARKING_NOT_AVAILABLE

    }
    private  ExceptionType type;


    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public ParkingLotException(String message) {
        super(message);
    }
}
