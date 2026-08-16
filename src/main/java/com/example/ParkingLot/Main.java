package com.example.ParkingLot;

import com.example.ParkingLot.entity.*;
import com.example.ParkingLot.enums.VehicleType;
import com.example.ParkingLot.stratergy.DefaultPricingStrategy;
import com.example.ParkingLot.stratergy.PricingStratergy;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("=== LLD Parking Lot Management System ===\n");
        
        
        //Initialize Parking Floor with empty spots
        List<ParkingFloor> floors = new ArrayList<>();
        
        // Floor 1 - with 5 car spots and 5 bike spots
        ParkingFloor floor1 = new ParkingFloor();
        floor1.setFloorNumber(1);
        floor1.setParkingSpotList(new ArrayList<>());
        
        // Add car spots (4-wheeler) to floor 1
        for (int i = 1; i <= 5; i++) {
            ParkingSpot carSpot = new ParkingSpot();
            carSpot.setSpotId(i);
            carSpot.setVehicleType(VehicleType.FOUR_WHEELER);
            carSpot.setOccupied(false);
            floor1.getParkingSpotList().add(carSpot);
        }
        
        // Add bike spots (2-wheeler) to floor 1
        for (int i = 6; i <= 10; i++) {
            ParkingSpot bikeSpot = new ParkingSpot();
            bikeSpot.setSpotId(i);
            bikeSpot.setVehicleType(VehicleType.TWO_WHEELER);
            bikeSpot.setOccupied(false);
            floor1.getParkingSpotList().add(bikeSpot);
        }
        floors.add(floor1);
        
        // Floor 2 - with 5 car spots and 5 bike spots
        ParkingFloor floor2 = new ParkingFloor();
        floor2.setFloorNumber(2);
        floor2.setParkingSpotList(new ArrayList<>());
        
        // Add car spots to floor 2
        for (int i = 11; i <= 15; i++) {
            ParkingSpot carSpot = new ParkingSpot();
            carSpot.setSpotId(i);
            carSpot.setVehicleType(VehicleType.FOUR_WHEELER);
            carSpot.setOccupied(false);
            floor2.getParkingSpotList().add(carSpot);
        }
        
        // Add bike spots to floor 2
        for (int i = 16; i <= 20; i++) {
            ParkingSpot bikeSpot = new ParkingSpot();
            bikeSpot.setSpotId(i);
            bikeSpot.setVehicleType(VehicleType.TWO_WHEELER);
            bikeSpot.setOccupied(false);
            floor2.getParkingSpotList().add(bikeSpot);
        }
        floors.add(floor2);
        
        System.out.println("✓ Parking lot initialized with 2 floors, 20 total spots");
        System.out.println("  Floor 1: 5 car spots + 5 bike spots");
        System.out.println("  Floor 2: 5 car spots + 5 bike spots\n");
        
        // Initialize pricing strategy
        PricingStratergy pricingStrategy = new DefaultPricingStrategy();
        
        // Initialize parking lot
        ParkingLot parkingLot = new ParkingLot(pricingStrategy);
        parkingLot.setFloors(floors);
        
        // Create and park a car
        Car car = new Car("TATA");
        car.setType(VehicleType.FOUR_WHEELER);
        car.setVehicleNumber("CAR-001");
        System.out.println("Attempting to park car: " + car.getVehicleNumber());
        ParkingSpot spot = parkingLot.parkVehicle(car);
        System.out.println("Car parked successfully! at "+spot.getSpotId());
        
        // Create and park a bike
        Bike bike = new Bike();
        bike.setType(VehicleType.TWO_WHEELER);
        bike.setVehicleNumber("BIKE-001");
        System.out.println("Attempting to park bike: " + bike.getVehicleNumber());
       ParkingSpot spot1 =  parkingLot.parkVehicle(bike);
        System.out.println("Bike parked successfully at "+spot1.getSpotId());
        
        System.out.println("=== Parking Lot Demo Complete ===");
    }
}

