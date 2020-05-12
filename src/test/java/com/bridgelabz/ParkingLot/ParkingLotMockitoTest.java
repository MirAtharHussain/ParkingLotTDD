package com.bridgelabz.ParkingLot;

import com.bridgelabz.ParkingLot.Exception.ParkingLotException;
import com.bridgelabz.ParkingLot.Mockito.ParkingLotMockito;
import com.bridgelabz.ParkingLot.ParkingSystem.ParkingLotSystem;
import com.bridgelabz.ParkingLot.VehicleInformation.Vehicle;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleMockito;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingLotMockitoTest {
        @Mock
        ParkingLotMockito parkingLot,lot1,lot2,lot3;
        VehicleMockito vehicle =null,vehicle1=null,vehicle2 = null,vehicle3=null,vehicle4=null,vehicle5=null;
        ParkingLotSystem lotSystem=null;
        @Before
        public void setUp() throws Exception {
            parkingLot=mock(ParkingLotMockito.class);
            vehicle = new VehicleMockito("BN12N1234");
            lotSystem=new ParkingLotSystem(parkingLot);
            vehicle1 = new VehicleMockito("BN24U2180");
            vehicle2 = new VehicleMockito("BN24U2181");
            vehicle3 = new VehicleMockito("BN24U2182");
            vehicle4 = new VehicleMockito("BN24U2183");
            vehicle5 = new VehicleMockito("BN24U2184");
        }

        @Test
        public void givenAVehicle_whenAttendantParked_ShouldReturnTrue() throws ParkingLotException {
            when(parkingLot.isVehicleParked(vehicle)).thenReturn(true);
            lotSystem.parkVehicle(vehicle);
            boolean vehicleParked = lotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(vehicleParked);
        }
        @Test
        public void givenAVehicle_whenAttendantUnParked_ShouldReturnFalse() throws ParkingLotException {
            when(parkingLot.isVehicleParked(vehicle)).thenReturn(false);
            lotSystem.parkVehicle(vehicle);
            lotSystem.unParkVehicle(vehicle);
            boolean vehicleParked = lotSystem.isVehicleParked(vehicle);
            Assert.assertFalse(vehicleParked);
        }
        @Test
        public void givenAVehicle_whenAttendantNotParked_ShouldReturnFalse()  {
            when(parkingLot.isVehicleParked(vehicle)).thenReturn(false);
            boolean isParked = lotSystem.isVehicleParked(vehicle);
            Assert.assertFalse(isParked);
        }
        @Test
        public void givenAVehicle_whenDriverWantToFind_ShouldReturnVehicleSlot() throws ParkingLotException {
            VehicleMockito vehicle2=new VehicleMockito("BN24U2181");
            when(parkingLot.findSlot(vehicle2)).thenReturn(2);
            lotSystem.parkVehicle(vehicle);
            lotSystem.parkVehicle(vehicle2);
            int slot= lotSystem.findVehicleSlot(vehicle2);
            Assert.assertEquals(2,slot);
        }
        @Test
        public void givenAVehicle_WhenAttendantParkedInEvenlyDistributedLots_ShouldReturnLotNumber() throws ParkingLotException {
            lot1=mock(ParkingLotMockito.class);
            lot2=mock(ParkingLotMockito.class);
            lot3=mock(ParkingLotMockito.class);
            ParkingLotSystem lotSystem = new ParkingLotSystem(lot1,lot2,lot3);
            when(lot1.findLotNumber(vehicle4)).thenReturn(1);
            lotSystem.parkVehicle(vehicle1);
            lotSystem.parkVehicle(vehicle2);
            lotSystem.parkVehicle(vehicle3);
            lotSystem.parkVehicle(vehicle4);
            lotSystem.parkVehicle(vehicle5);
            int lot = lotSystem.findVehicleLotNumber(vehicle4);
            Assert.assertEquals(1,lot);
        }

    }
