package com.bridgelabz.ParkingLot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ParkingLotTest {

    public Vehicle vehicle0;
    public Vehicle vehicle1;
    public Vehicle vehicle2;
    public Vehicle vehicle3;
    ParkingLotSystem parkingLotSystem;

    ParkingLotAttendant attendant;
    ArrayList<Object> details;
    ArrayList<Object> details1;
    ArrayList<Object> details2;


    @Before
    public void setUp() throws Exception {
        vehicle0 = new Vehicle("KA01RG007");
        vehicle1 = new Vehicle("KA01RG0060");
        vehicle2 = new Vehicle("KA01GF0010");
        vehicle2 = new Vehicle("KA01RG9294");

        parkingLotSystem = new ParkingLotSystem(2);
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        attendant = new ParkingLotAttendant(parkingLot1, parkingLot2);
        details = new ArrayList<>();
        details.add(Color.WHITE);
        details1 = new ArrayList<>();
        details1.add(Color.BLACK);
        details2 = new ArrayList<>();
        details2.add(Color.BLUE);

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
        try {
            parkingLotSystem.park(vehicle0, details);
            parkingLotSystem.park(vehicle1, details1);
            parkingLotSystem.park(vehicle2, details2);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnPosition() {
        try {
            attendant.parkVehicle(vehicle0, details);
            int[] unPark = parkingLotSystem.unPark(vehicle0);
            int[] result = {0, 1};
            Assert.assertArrayEquals(result, unPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenNoVehicleParked_WhenUnPark_ShouldReturnFalse() {
        try {
            int[] unPark = parkingLotSystem.unPark(vehicle0);
            int[] result = {0, 1};
            Assert.assertArrayEquals(result, unPark);
        } catch (ParkingLotException e) {
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
            e.printStackTrace();
        }
    }

    @Test
    public void givenWhenParkingLot_ISFull_ShouldInformTheOwner() {
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
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            attendant.parkVehicle(vehicle0, details);
            attendant.parkVehicle(vehicle1, details1);
        } catch (ParkingLotException e) {
        }
        attendant.unparkVehicle(vehicle0);
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
            Assert.assertEquals("Vehicle Already Parked", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAsParkingLotOwner_WantParkingAttendant_ToParkcars() throws ParkingLotException {
        int[] parkedPosition = null;
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        boolean capacityFull = owner.isCapacityFull();
        if (!capacityFull) {
            parkedPosition = owner.parkingLotAttendant(vehicle0, details);
        }
        int[] result = {0, 1};
        Assert.assertArrayEquals(result, parkedPosition);
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
    public void givenParkingLotOwner_WantParkingAttendant_ToEvenlyDirectCars_ToLots() throws ParkingLotException {

        int[] car = attendant.parkVehicle(vehicle0, details);
        int[] car1 = attendant.parkVehicle(vehicle1, details1);
        System.out.println(Arrays.toString(car1));
        int[] result = {1, 1};
        int[] result1 = {0, 1};
        Assert.assertArrayEquals(result1, car);
        Assert.assertArrayEquals(result, car1);
    }
}


