package com.example.ParkingLot.entity;

public class Car extends Vehicle {

    int id;
    String brand;

    public Car(String brand){
        this.brand = brand;
    }
    @Override
    int calculateFee(int hours){
        return hours  * 10;
    }


}
