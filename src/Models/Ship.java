package Models;

import Models.Containers.ContBasic;
import com.company.StaticClasses;

public class Ship {
    //basic info
    String name;
    String homeport;
    String from;
    String destination;

    //ID.
    private String shipId;

    //ship capacity
    ShipCapacityInfo shipCapacityMax;

    public Ship(String name,String homeport,String from,String destination, ShipCapacityInfo shipCapacityMax){

        shipId = StaticClasses.IdGenerator.Generate();

        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;

        this.shipCapacityMax = shipCapacityMax;

    }

    public void loadContainer(ContBasic container){
        int newCapacity;
        double newWeight;

    }
}
