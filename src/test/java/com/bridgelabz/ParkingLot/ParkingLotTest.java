package com.bridgelabz.ParkingLot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isPraked = parkingLotSystem.park(new Object());
        Assert.assertTrue(isPraked);
    }
}
