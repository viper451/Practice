package com.example.ParkingLot.stratergy;

import com.example.ParkingLot.entity.Vehicle;
import com.example.ParkingLot.enums.VehicleType;

public class DefaultPricingStrategy implements PricingStratergy {

    @Override
    public double calculatePrice(VehicleType vehicle, int hours) {

        switch (vehicle) {

            case FOUR_WHEELER:
                return hours * 20;

            case TWO_WHEELER:
                return hours * 10;

            default:
                return hours * 50;
        }
    }
}
