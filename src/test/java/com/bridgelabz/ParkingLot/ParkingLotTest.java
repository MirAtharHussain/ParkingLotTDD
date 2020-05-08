package com.bridgelabz.ParkingLot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ParkingLotTest {

    public Vehicle vehicle0 = null;
    public Vehicle vehicle1 = null;
    public Vehicle vehicle2 = null;
    public Vehicle vehicle3 = null;
    ParkingLotSystem parkingLotSystem = null;

    ParkingLotAttendant attendant;
    VehicleDetails details;
    VehicleDetails details1;
    VehicleDetails details2;


    @Before
    public void setUp() throws Exception {
        vehicle0 = new Vehicle("KA01RG007");
        vehicle1 = new Vehicle("KA01RG0060");
        vehicle2 = new Vehicle("KA01GF0010");
        vehicle2 = new Vehicle("KA01RG9294");

        parkingLotSystem = new ParkingLotSystem(8);
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(5);

        attendant = new ParkingLotAttendant(parkingLot1, parkingLot2);

        details = new VehicleDetails(VEhicleAttributes.Driver.HANDICAP, VEhicleAttributes.Color.BLUE, VEhicleAttributes.Size.LARGE, VEhicleAttributes.Model.TOYOTA);
        details1 = new VehicleDetails(VEhicleAttributes.Driver.NORMAL, VEhicleAttributes.Color.WHITE, VEhicleAttributes.Size.LARGE, VEhicleAttributes.Model.BMW);
        details2 = new VehicleDetails(VEhicleAttributes.Driver.HANDICAP, VEhicleAttributes.Color.WHITE, VEhicleAttributes.Size.SMALL, VEhicleAttributes.Model.TOYOTA);

    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle0, details);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle0);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle1, details1);
            parkingLotSystem.park(vehicle2, details2);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_FULL, e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnPosition() {
        try {
            attendant.parkVehicle(vehicle0, details);
            int[] unPark = attendant.unparkVehicle(vehicle0);
            int[] result = {0, 1};
            Assert.assertArrayEquals(result, unPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenNoVehicleParked_WhenUnPark_ShouldReturnException() {
        try {
            int[] unPark = parkingLotSystem.unPark(vehicle0);
            int[] result = {0, 1};
            Assert.assertArrayEquals(result, unPark);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_VEHICLE_FOUND, e.type);
            e.printStackTrace();
        }

    }

    @Test
    public void givenAOtherVehicle_WhenUnparked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle0, details);
            int[] unparkVehicle = attendant.unparkVehicle(vehicle1);
            int[] result = {0, 1};
            Assert.assertArrayEquals(result, unparkVehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_VEHICLE_FOUND, e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenWhenParkingLot_ISFull_ShouldInformTheOwner() {
        parkingLotSystem.setCapacity(2);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle1, details1);
            parkingLotSystem.park(vehicle2, details2);
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle2, details1);
            boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle0);
            boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        parkingLotSystem.setCapacity(2);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle1, details1);
            parkingLotSystem.park(vehicle2, details2);
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle1, details2);
        } catch (ParkingLotException e) {
        }
        parkingLotSystem.unPark(vehicle0);
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull__InformSecurity_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle1, details2);
            parkingLotSystem.unPark(vehicle0);
        } catch (ParkingLotException e) { }

        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void givenSameVehicle_WhenAlreadyParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle1, details1);
            parkingLotSystem.park(vehicle0, details);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED, e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenAsParkingLotOwner_WantParkingAttendant_ToParkcars() throws ParkingLotException {
        boolean vehicleParked = false;
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        boolean capacityFull = owner.isCapacityFull();
        if (!capacityFull) {
            parkingLotSystem.park(vehicle0, details);
            vehicleParked = parkingLotSystem.isVehicleParked(vehicle0);
        }
      Assert.assertTrue(vehicleParked);
    }

    @Test
    public void givenFindMyVehicle_WhenVehicleIsParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle2, details2);
            boolean myVehicle = parkingLotSystem.findMyVehicle(vehicle2);
            Assert.assertTrue(myVehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenFindMyVehicle_WhenVehicleIsNotParked_ShouldReturnException() {
        try {
            parkingLotSystem.park(vehicle2, details1);
            parkingLotSystem.findMyVehicle(vehicle0);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE_NOT_FOUND", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotOwner_WantParkingAttendant_ToEvenlyDirectCars_ToLots()  {

        try {
            int[] car = attendant.parkVehicle(vehicle0, details);
            int[] car1 = attendant.parkVehicle(vehicle1, details1);
            System.out.println(Arrays.toString(car));
            int[] result = {0, 1};
            Assert.assertArrayEquals(result, car);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenHandicapDriver_WantAttendantToParkVehicle() {
        try {
            int[] car = attendant.parkVehicle(vehicle0, details);
            int[] car1 = attendant.parkVehicle(vehicle1, details1);
            int[] result = {1, 1};
            int[] result1 = {0, 1};
            Assert.assertArrayEquals(result1, car);
            Assert.assertArrayEquals(result, car1);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }


    }
}


