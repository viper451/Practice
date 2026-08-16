package com.example.ParkingLot.entity;

import com.example.ParkingLot.enums.VehicleType;
import com.example.ParkingLot.stratergy.PricingStratergy;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class Ticket {
    private String id;
    private int spotId;
    private VehicleType vehicleType;
    private long entryTime;

    public static Ticket generateTicket(ParkingSpot spot, PricingStratergy pricingStratergy) {
        if (spot == null || pricingStratergy == null) return null;

        // compute cost for demo (7 hours) and print
        int cost = (int) pricingStratergy.calculatePrice(spot.getVehicleType(), 7);
        System.out.println("COST OF TICKET = " + cost);

        Ticket t = new Ticket();
        t.id = UUID.randomUUID().toString();
        t.spotId = spot.getSpotId();
        t.vehicleType = spot.getVehicleType();
        t.entryTime = Instant.now().toEpochMilli();
        return t;
    }
}
