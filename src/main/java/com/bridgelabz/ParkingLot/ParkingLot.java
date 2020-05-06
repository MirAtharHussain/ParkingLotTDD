package com.bridgelabz.ParkingLot;

public class ParkingLot {


    private  int size;
    private  int parkingLotCapacity;

    public ParkingLot(int capacity) {
      this.parkingLotCapacity = capacity;
      this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return parkingLotCapacity;
    }
}
