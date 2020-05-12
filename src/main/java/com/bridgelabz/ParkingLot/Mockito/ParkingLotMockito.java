package com.bridgelabz.ParkingLot.Mockito;

import com.bridgelabz.ParkingLot.VehicleInformation.Vehicle;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleMockito;

public class ParkingLotMockito {
    private VehicleMockito vehicle=null;

    public ParkingLotMockito(int i) {
    }

    public void park(VehicleMockito vehicle) {
        this.vehicle=vehicle;
    }

    public boolean isVehicleParked(VehicleMockito vehicle) {
        return false;
    }

    public void unPark(VehicleMockito vehicle) {
        this.vehicle=null;
    }

    public int findSlot(VehicleMockito vehicle) {
        return 0;
    }

    public int findLotNumber(VehicleMockito vehicle) {
        return 0;
    }
}
