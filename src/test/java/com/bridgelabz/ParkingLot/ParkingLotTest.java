package com.bridgelabz.ParkingLot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ParkingLotTest {

    public Vehicle vehicle0;
    public Vehicle vehicle1;
    public Vehicle vehicle2;
    ParkingLotSystem parkingLotSystem;

    @Before
    public void setUp() throws Exception {
        vehicle0 = new Vehicle("KA01RG007");
        vehicle1 = new Vehicle("KA01RG0060");
        vehicle2 = new Vehicle("KA01RG0010");
        parkingLotSystem = new ParkingLotSystem(2);
    }
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle0);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle0);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle0);
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_FULL,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle0);
            boolean isUnparked = parkingLotSystem.unPark(vehicle0);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenNoVehicleParked_WhenUnPark_ShouldReturnFalse() {
        vehicle0 = null;
        try {
            parkingLotSystem.park(vehicle0);
            boolean isUnparked = parkingLotSystem.unPark(vehicle0);
            Assert.assertFalse(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAOtherVehicle_WhenUnparked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle0);
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
            parkingLotSystem.park(vehicle0);
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotException e) { }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle0);
            parkingLotSystem.park(vehicle2);
            boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle0);
            boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) { }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle0);
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotException e) { }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle0);
            parkingLotSystem.park(vehicle1);
        } catch (ParkingLotException e) { }
        parkingLotSystem.unPark(vehicle0);
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }
    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull__InformSecurity_ShouldReturnTrue() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle0);
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotException e) { }
        parkingLotSystem.unPark(vehicle0);
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }
    @Test
    public void givenSameVehicle_WhenAlreadyParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle0);
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle0);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_PARKED_ALREADY,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenAsParkingLotOwner_WantParkingAttendant_ToParkcars() {
        boolean vehicleParked = false;
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        boolean capacityFull = owner.isCapacityFull();
        if (!capacityFull) {
            vehicleParked = owner.parkingLotAttendant(vehicle0);
        }
        Assert.assertTrue(vehicleParked);
    }

    @Test
    public void givenFindMyVehicle_WhenVehicleIsParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle0);
            parkingLotSystem.park(vehicle2);
            boolean myVehicle = parkingLotSystem.findMyVehicle(vehicle2);
            Assert.assertTrue(myVehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void givenFindMyVehicle_WhenVehicleIsNotParked_ShouldReturnException() {
        try {
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.findMyVehicle(vehicle0);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,e.type);
            e.printStackTrace();
        }

    }
}


