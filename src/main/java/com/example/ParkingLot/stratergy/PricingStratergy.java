package com.example.ParkingLot.stratergy;

import com.example.ParkingLot.entity.Vehicle;
import com.example.ParkingLot.enums.VehicleType;

public interface PricingStratergy {



    double calculatePrice(VehicleType vehicle, int hours);
}
