package Models;

import com.company.StaticClasses;

public class Ship extends ShipCapacityInfo {
    //basic info
    String name;
    String homeport;
    String from;
    String destination;

    //ID.
    String shipId;

    public Ship(String name,String homeport,String from,String destination, int maxCapacity,double maxWeight,int maxHeavy,int maxElectrified,int maxHazzardous){

        super(maxCapacity ,maxWeight ,maxHeavy ,maxElectrified ,maxHazzardous);

        shipId = StaticClasses.IdGenerator.Generate();

        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;

    }
}
