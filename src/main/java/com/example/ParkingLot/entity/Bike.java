package com.example.ParkingLot.entity;

public class Bike extends Vehicle {


    @Override
    int calculateFee(int hours){
        return hours * 10;
    }
}
