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
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) { }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        Object vehicle2 = new Object();
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
            boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) { }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) { }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() {
        Object vehicle2 = new Object();
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotException e) { }
        parkingLotSystem.unPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }
}
