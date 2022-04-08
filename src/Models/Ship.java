package Models;

import com.company.StaticClasses;

public class Ship {
    //basic info
    String name;
    String homeport;
    String from;
    String destination;

    //ID.
    String shipId;

    ShipCapacityInfo shipCapacityInfo;

    public Ship(String name,String homeport,String from,String destination, ShipCapacityInfo shipCapacityInfo){

        shipId = StaticClasses.IdGenerator.Generate();

        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;

        this.shipCapacityInfo = shipCapacityInfo;

    }
}
