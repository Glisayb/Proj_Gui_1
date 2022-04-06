package com.company;

public class Ship {
    //basic info
    String name;
    String homeport;
    String from;
    String destination;

    //ID.
    String shipId;

    //capacity info
    int maxCapacity;
    double maxWeight;
    int maxHeavy;
    int maxElectrified;
    int maxHazzardous;

    Ship(String name,String homeport,String from,String destination,int maxCapacity,double maxWeight,int maxHeavy,int maxElectrified,int maxHazzardous){

        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;

        this.maxCapacity = maxCapacity;
        this.maxWeight = maxWeight;
        this.maxHeavy = maxHeavy;
        this.maxElectrified = maxElectrified;
        this.maxHazzardous = maxHazzardous;

    }
}
