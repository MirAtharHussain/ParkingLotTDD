package com.bridgelabz.ParkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    Object vehicle = null;
    ParkingLotSystem parkingLotSystem = null;

    @Before
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem(1);
    }
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot is Full",e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnparked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenNoVehicleParked_WhenUnPark_ShouldReturnFalse() {
        vehicle = null;
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnparked = parkingLotSystem.unPark(vehicle);
            Assert.assertFalse(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAOtherVehicle_WhenUnparked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnparked = parkingLotSystem.unPark(new Object());
            Assert.assertFalse(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWhenParkingLot_ISFull_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerOwner(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) { }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }
}
