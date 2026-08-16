package com.example.ParkingLot.entity;

import com.example.ParkingLot.enums.VehicleType;
import lombok.Data;

@Data
public class ParkingSpot {
    private int spotId;
    private VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle vehicle;

    /**
     * Try to park a vehicle into this spot. Returns true if successful.
     */
    public synchronized boolean park(Vehicle v) {
        if (v == null || isOccupied || v.getType() != this.vehicleType) return false;
        this.vehicle = v;
        this.isOccupied = true;
        return true;
    }

    /**
     * Unpark and return the vehicle (or null if none).
     */
    public synchronized Vehicle unpark() {
        if (!isOccupied) return null;
        Vehicle v = this.vehicle;
        this.vehicle = null;
        this.isOccupied = false;
        return v;
    }

}
