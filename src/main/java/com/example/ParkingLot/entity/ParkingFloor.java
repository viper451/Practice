package com.example.ParkingLot.entity;

import java.util.List;
import lombok.Data;

@Data
public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpotList;


    public ParkingSpot getAvailableSpot(Vehicle vehicle){
         // defensive checks
         if (vehicle == null || parkingSpotList == null) return null;

         for (int i = 0; i < parkingSpotList.size(); i++) {
             ParkingSpot spot = parkingSpotList.get(i);
             // Compare enum values using == (safe) and avoid calling equals on a possibly null field.
             if (!spot.isOccupied() && spot.getVehicleType() == vehicle.getType()) {
                 return spot;
             }
         }
         return null;
    }


}
