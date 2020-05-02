package com.bridgelabz.ParkingLot;

public class ParkingLotSystem {
    private Object vehicle;

    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking Lot is Full");
        this.vehicle = vehicle;
    }

    public boolean unPark(Object vehicle)  {
        if (vehicle == null) return false;
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}
