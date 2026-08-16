package com.example.ParkingLot.entity;

import com.example.ParkingLot.stratergy.PricingStratergy;
import lombok.Data;

import java.util.List;

@Data
public class ParkingLot {
    private List<ParkingFloor> floors;

    private PricingStratergy pricingStratergy;

    public ParkingLot(PricingStratergy pricingStrategy) {
        this.pricingStratergy = pricingStrategy;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle){
        ParkingSpot spot = null;

        for (ParkingFloor floor : floors) {
                 spot = floor.getAvailableSpot(vehicle);
                 if(spot != null ) break;
             }

        Ticket ticket = Ticket.generateTicket(spot, pricingStratergy);
        System.out.println(ticket.toString());
         return spot;
    }
}
