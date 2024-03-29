package com.bridgelabz.ParkingLot.ParkingSystem;

import com.bridgelabz.ParkingLot.Exception.ParkingLotException;
import com.bridgelabz.ParkingLot.Exception.Row;
import com.bridgelabz.ParkingLot.StratergyPattern.VehiceLocByColorImpl;
import com.bridgelabz.ParkingLot.StratergyPattern.VehicleLocByColorAndModelImpl;
import com.bridgelabz.ParkingLot.StratergyPattern.VehicleLocByModel;
import com.bridgelabz.ParkingLot.VehicleInformation.Vehicle;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleDetails;
import com.bridgelabz.ParkingLot.VehicleInformation.VehicleProperties;
import com.bridgelabz.ParkingLot.enums.ParkingType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLot {

    private  int actualCapacity;
    public List<ParkingSlot> parkingSlotsList;
    public VehiceLocByColorImpl color =  new VehiceLocByColorImpl();
    public VehicleLocByModel model = new VehicleLocByModel();
    public VehicleLocByColorAndModelImpl colorAndModel = new VehicleLocByColorAndModelImpl();

    public ParkingLot(int capacity) {
        this.parkingSlotsList = new ArrayList<>();
        this.actualCapacity = capacity;
        for (int i=1; i<=actualCapacity; i++){
            parkingSlotsList.add(new ParkingSlot(i, null, null));
        }
    }

    public boolean parkVehicleInToSlots(Vehicle vehicle, VehicleDetails details) throws ParkingLotException {
        Integer parkingSlots = details.parkingType.getParkingSlots(parkingSlotsList);
        if (this.parkingSlotsList.contains(vehicle))
            throw new ParkingLotException("Vehicle Already Parked", ParkingLotException.ExceptionType.VEHICLE_PARKED_ALREADY);
        if (parkingSlots==0)
            throw new ParkingLotException("Parking Lot is Full", ParkingLotException.ExceptionType.PARKINGLOT_FULL);
        parkingSlotsList.get(parkingSlots - 1).setVehicle(vehicle);
        parkingSlotsList.get(parkingSlots - 1).setTime(LocalDateTime.now());
        return true;

    }

    public boolean unparkVehicle(Vehicle vehicle) throws ParkingLotException {
        int parkedVehicle = findVehicle(vehicle);
        parkingSlotsList.get(parkedVehicle - 1).setVehicle(null);
        parkingSlotsList.get(parkedVehicle - 1).setVehicle(null);
        return true;
    }

    public int findVehicle(Vehicle vehicle) throws ParkingLotException {
        ParkingSlot parkingSlot = this.parkingSlotsList.stream().
                filter(parkingSlotsList -> vehicle.equals(parkingSlotsList.getVehicle())).
                findFirst().orElseThrow(() -> new ParkingLotException("VEHICLE_NOT_FOUND", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND));
        return parkingSlot.getSlotNum();

    }

    public List<Integer> getParkedVehiclesSlotNumbers(VehicleProperties vehicleProperties) {
        if (vehicleProperties.equals(vehicleProperties.color))
            return color.getLocation(this.parkingSlotsList, vehicleProperties);
        else if (vehicleProperties.equals(vehicleProperties.model))
            return  model.getLocation(this.parkingSlotsList, vehicleProperties);
        else
            return colorAndModel.getLocation(this.parkingSlotsList, vehicleProperties);
    }


    public ArrayList<Vehicle> getParkedVehicleInLast30Min() {
        return parkingSlotsList.stream().
                filter(parkingSlot -> parkingSlot.getVehicle() != null &&
                        (parkingSlot.getTime().getMinute() - LocalDateTime.now().getMinute() <= 30)).
                 map(ParkingSlot::getVehicle).collect(Collectors.toCollection(ArrayList::new));
    }


    public List getVehiclesParkedInRowBOrD(ParkingType type) {
        return parkingSlotsList.stream().
                filter(parkingSlot -> parkingSlot.getVehicle() != null &&
                                type.equals(type)).
                        map(ParkingSlot::getSlotNum).
                                collect(Collectors.toCollection(ArrayList::new));
    }
}
