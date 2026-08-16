package com.example.ParkingLot.entity;

import com.example.ParkingLot.enums.VehicleType;
import lombok.Data;

@Data
public abstract class Vehicle {
    private String vehicleNumber;
    private VehicleType type;

    // keep calculateFee for subclasses to override
    int calculateFee(int hours){
        return 0;
    }
}
