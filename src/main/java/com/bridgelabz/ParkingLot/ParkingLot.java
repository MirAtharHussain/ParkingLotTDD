package com.bridgelabz.ParkingLot;

public class ParkingLot {
    Object [] space = null;

    public ParkingLot(int capacity) {
        space = new Object[capacity];
        space[0]=0;
    }
}