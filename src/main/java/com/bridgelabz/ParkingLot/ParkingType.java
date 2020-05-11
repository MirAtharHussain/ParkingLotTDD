package com.bridgelabz.ParkingLot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public enum ParkingType {

        NORMAL {
            @Override
            public ParkingLot getParkingLot(List<ParkingLot> parkingLotList) {
                ArrayList<ParkingLot> sortList = new ArrayList<>();
                sortList.addAll(parkingLotList);
                sortList.sort(Comparator.comparing(parkingLot ->{
                    long count = parkingLot.parkingSlotsList.stream().
                            filter(parkingSlot -> parkingSlot.getVehicle() == null).count();
                    return  count;
                }));
                return sortList.get(0);
            }

            @Override
            public Integer getParkingSlots(List<ParkingSlot> parkingSlotsList) {
                return (IntStream.range(0, parkingSlotsList.size()).
                        filter(i -> parkingSlotsList.get(i).getVehicle()==null).findFirst().orElse(-1))+1;
            }
        },
        HANDICAP{
            @Override
            public ParkingLot getParkingLot(List<ParkingLot> parkingLotList) {
                for (ParkingLot parkingLot:parkingLotList) {
                    //  parkingLot.parkingSlotsList.forEach(parkingSlot -> {if (parkingSlot.getVehicle() == null){return parkingLot;};});
                    for (ParkingSlot parkingSlot:parkingLot.parkingSlotsList) {
                        if (parkingSlot.getVehicle() == null){
                            return parkingLot;
                        }

                    }
                }
                return null;
            }

            @Override
            public Integer getParkingSlots(List<ParkingSlot> parkingSlotsList) {
                return (IntStream.range(0, parkingSlotsList.size()).
                        filter(i -> parkingSlotsList.get(i).getVehicle()==null).findFirst().orElse(-1))+1;
            }
        },
    LARGE{
        @Override
        public ParkingLot getParkingLot(List<ParkingLot> parkingLotList) {
            for (ParkingLot parkingLot:parkingLotList) {
               for (int i=0; i<=parkingLot.parkingSlotsList.size(); i++){
                   if (parkingLot.parkingSlotsList.get(i).getVehicle()==null && parkingLot.parkingSlotsList.get(i+1).getVehicle()==null){
                       return parkingLot;
                    }

                }
            }
            return null;
        }

        @Override
        public Integer getParkingSlots(List<ParkingSlot> parkingSlotsList) {
            return (IntStream.range(0, parkingSlotsList.size()).
                    filter(i -> parkingSlotsList.get(i).getVehicle()==null && parkingSlotsList.get(i+1).getVehicle()==null).findFirst().orElse(-1))+1;
        }
    };

        public   ParkingType type;

    public abstract ParkingLot getParkingLot(List<ParkingLot> parkingLotList);

        public abstract Integer getParkingSlots(List<ParkingSlot> parkingSlotsList);
}