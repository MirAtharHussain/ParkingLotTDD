package com.bridgelabz.ParkingLot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {

    public Vehicle vehicle0;
    public Vehicle vehicle1;
    public Vehicle vehicle2;
    public Vehicle vehicle3;
    public Vehicle vehicle4;

    ParkingLotSystem parkingLotSystem;
    ParkingLot lot1;
    ParkingLot lot2;

    VehicleDetails detail1;
    VehicleDetails detail2;
    VehicleDetails detail3;
    VehicleDetails detail4;
    VehicleDetails detail5;
    VehicleDetails detail6;

    @Before
    public void setUp() throws Exception {
        vehicle0 = new Vehicle("KA01RG0307", "Raj", VehicleProperties.Color.BLUE, VehicleProperties.Model.TOYOTA);
        vehicle1 = new Vehicle("KA01RG0640", "Akshay", VehicleProperties.Color.WHITE, VehicleProperties.Model.MERCEDES);
        vehicle2 = new Vehicle("KA01RG0105", "Rahul", VehicleProperties.Color.RED, VehicleProperties.Model.TOYOTA);
        vehicle3 = new Vehicle("KA01RG5346", "Saif", VehicleProperties.Color.WHITE, VehicleProperties.Model.BMW);
        vehicle4 = new Vehicle("KA01RG6455", "Lohith", VehicleProperties.Color.WHITE, VehicleProperties.Model.TOYOTA);

        detail1 = new VehicleDetails(ParkingType.NORMAL);
        detail2 = new VehicleDetails(ParkingType.HANDICAP);
        detail3 = new VehicleDetails(ParkingType.HANDICAP);
        detail4 = new VehicleDetails(ParkingType.NORMAL);
        detail5 = new VehicleDetails(ParkingType.LARGE);
        detail6 = new VehicleDetails(ParkingType.LARGE);


        lot1 = new ParkingLot(2);
        lot2 = new ParkingLot(2);
        parkingLotSystem = new ParkingLotSystem(lot1, lot2);
    }

    //****UC1
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            lot1.parkVehicleInToSlots(vehicle0, detail2);
            int vehicle = lot1.findVehicle(vehicle0);
            Assert.assertEquals(1, vehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            lot1.parkVehicleInToSlots(vehicle0, detail1);
            lot1.parkVehicleInToSlots(vehicle1, detail2);
            lot1.parkVehicleInToSlots(vehicle2, detail3);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_FULL, e.type);
            e.printStackTrace();
        }
    }

    //****UC2
    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            lot1.parkVehicleInToSlots(vehicle0, detail2);
            boolean isUnparked = lot1.unparkVehicle(vehicle0);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenNoVehicleParked_WhenUnPark_ShouldReturnFalse() {
        try {
            boolean isUnparked = lot1.unparkVehicle(vehicle0);
            Assert.assertFalse(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAOtherVehicle_WhenUnparked_ShouldReturnFalse() {
        try {
            lot1.parkVehicleInToSlots(vehicle0, detail2);
            boolean isUnparked = lot1.unparkVehicle(vehicle1);
            Assert.assertFalse(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    //*****UC3
    @Test
    public void givenWhenParkingLot_ISFull_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle0, detail1);
            parkingLotSystem.park(vehicle1, detail2);
            parkingLotSystem.park(vehicle2, detail3);
            parkingLotSystem.park(vehicle4, detail2);
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        try {
            lot1.parkVehicleInToSlots(vehicle0, detail1);
            lot1.parkVehicleInToSlots(vehicle1, detail2);
            int isParked1 = lot1.findVehicle(vehicle0);
            int isParked2 = lot1.findVehicle(vehicle1);
            Assert.assertEquals(1, isParked1);
            Assert.assertEquals(2, isParked2);
        } catch (ParkingLotException e) {
        }
    }

    //*****UC4
    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle0, detail1);
            parkingLotSystem.park(vehicle1, detail2);
            parkingLotSystem.park(vehicle2, detail3);
            parkingLotSystem.park(vehicle3, detail4);
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    //****UC5
    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle0, detail1);
            parkingLotSystem.park(vehicle1, detail2);
            parkingLotSystem.park(vehicle2, detail3);
            parkingLotSystem.park(vehicle3, detail4);
            parkingLotSystem.unPark(vehicle0, detail1);
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull__InformSecurity_ShouldReturnTrue() throws ParkingLotException {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle0, detail1);
            parkingLotSystem.park(vehicle1, detail2);
            parkingLotSystem.park(vehicle2, detail3);
            parkingLotSystem.park(vehicle3, detail4);
            parkingLotSystem.unPark(vehicle0, detail1);
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void givenSameVehicle_WhenAlreadyParked_ShouldReturnTrue() {
        try {
            lot1.parkVehicleInToSlots(vehicle0, detail2);
            lot1.parkVehicleInToSlots(vehicle0, detail2);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_PARKED_ALREADY, e.type);
            e.printStackTrace();
        }
    }

    //****Uc6
    @Test
    public void givenAsParkingLotOwner_WantParkingAttendant_ToParkcars() throws ParkingLotException {
        boolean vehicleParked = false;
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        boolean capacityFull = owner.isCapacityFull();
        if (!capacityFull) {
            vehicleParked = parkingLotSystem.park(vehicle0, detail1);
        }
        Assert.assertTrue(vehicleParked);
    }

    @Test
    public void givenFindMyVehicle_WhenVehicleIsParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle0, detail1);
            parkingLotSystem.park(vehicle2, detail3);
            ParkingLot myVehicle = parkingLotSystem.findMyVehicle(vehicle0);
            Assert.assertEquals(lot1, myVehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    //****UC7
    @Test
    public void givenFindMyVehicle_WhenVehicleIsNotParked_ShouldReturnException() {
        try {
            parkingLotSystem.park(vehicle2, detail1);
            ParkingLot myVehicle = parkingLotSystem.findMyVehicle(vehicle0);
            Assert.assertEquals(lot1, myVehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, e.type);
            e.printStackTrace();
        }
    }

    //*****UC9
    @Test
    public void givenPrakingLotOwner_WantAttendant_ToParkVehicleInEvenlyManner() throws ParkingLotException {
        parkingLotSystem.park(vehicle0, detail1);//parkinglot 1
        parkingLotSystem.park(vehicle1, detail2);//parkinglot 2
        parkingLotSystem.park(vehicle2, detail3);//parkinglot 1
        parkingLotSystem.park(vehicle3, detail4);//parkinglot 2
        ParkingLot myVehicle = parkingLotSystem.findMyVehicle(vehicle0);
        Assert.assertEquals(lot1, myVehicle);
        ParkingLot myVehicle1 = parkingLotSystem.findMyVehicle(vehicle1);
        Assert.assertEquals(lot1, myVehicle1);

    }

    //*****UC10
    @Test
    public void givenHandicapDriverVehicle_ParkToNearestFreeSpace() throws ParkingLotException {

        parkingLotSystem.park(vehicle0, detail1);
        parkingLotSystem.park(vehicle1, detail2);
        int vehicle = lot1.findVehicle(vehicle1);
        Assert.assertEquals(2, vehicle);

    }

    @Test
    public void givenParkingLotOwner_WantLargeToPark_InLotWhichHasHigheshFreeSpace() throws ParkingLotException {
        parkingLotSystem.park(vehicle0, detail5);//lot1
        parkingLotSystem.park(vehicle1, detail1);
        ParkingLot myVehicle = parkingLotSystem.findMyVehicle(vehicle1);
        Assert.assertEquals(lot2, myVehicle);
    }

    @Test
    public void givenPoliceDepartment_WantToKnowLocationOfAllParkedWhiteCars() throws ParkingLotException {
        parkingLotSystem.park(vehicle0, detail1);
        parkingLotSystem.park(vehicle1, detail2);
        parkingLotSystem.park(vehicle2, detail3);
        parkingLotSystem.park(vehicle3, detail4);
        ArrayList<List<Integer>> location = parkingLotSystem.getLocation(new VehicleProperties(VehicleProperties.Color.WHITE));
        for (int i = 0; i < location.size(); i++) {
            System.out.println(location);
        }
    }

    @Test
    public void givenPoliceDepartmentWantsToKnow_LocationAttendantName_OfBlueToyota() throws ParkingLotException {
        Integer locationNumber = 0;
        parkingLotSystem.park(vehicle0, detail1);
        parkingLotSystem.park(vehicle1, detail2);
        parkingLotSystem.park(vehicle2, detail3);
        parkingLotSystem.park(vehicle3, detail4);
        ArrayList<List<Integer>> location = parkingLotSystem.getLocation(new VehicleProperties(VehicleProperties.Color.BLUE, VehicleProperties.Model.TOYOTA));
        for (List<Integer> i : location) { // iterate -list by list
            for (Integer position : i)  //iterate element by element in a list
                locationNumber = position;

        }
        Integer expected = 1;
        Assert.assertEquals(expected, locationNumber);
        Assert.assertEquals("Raj", vehicle0.getAttendantName());
    }

    @Test
    public void givenPoliceDepartmentWant_ToKnow_AllBMWParkedLocation() throws ParkingLotException {
        Object[] locationNumber = null;
        parkingLotSystem.park(vehicle0, detail1);
        parkingLotSystem.park(vehicle1, detail2);
        parkingLotSystem.park(vehicle2, detail3);
        parkingLotSystem.park(vehicle3, detail4);
        List<Integer> locationByModel = lot1.getLocationByModel(new VehicleProperties(VehicleProperties.Model.BMW));
        for (Integer i : locationByModel) {
            System.out.println(i);
        }
    }

  /*  @Test
    public void givenPoliceDepartmentWantsToKnow_VehiclesParkedInLast30Mins() throws ParkingLotException {
        parkingLotSystem.park(vehicle0, detail1);
        parkingLotSystem.park(vehicle1, detail2);
        ArrayList<ArrayList<Vehicle>> vehicleParkedInLast30Minutes = parkingLotSystem.getVehicleParkedInLast30Minutes();
    Assert.assertEquals(2, vehicleParkedInLast30Minutes.size());


    }*/
}



