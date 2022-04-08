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
    String shipId;

    //ship capacity
    ShipCapacityInfo shipCapacityMax;
    ShipCapacityInfo shipCapacityCurent;

    public Ship(String name,String homeport,String from,String destination, ShipCapacityInfo shipCapacityMax, ShipCapacityInfo shipCapacityCurent ){

        shipId = StaticClasses.IdGenerator.Generate();

        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;

        this.shipCapacityMax = shipCapacityMax;
        this.shipCapacityCurent = shipCapacityCurent;

    }

    public void loadContainer(ContBasic container){
        int newCapacity;
        double newWeight;

        //newCapacity = shipCapacityCurent.capacity + 1;
        //newWeight = shipCapacityCurent.weight + container.getWeight();

        //if (newWeight <= shipCapacityMax.weight && newCapacity <= shipCapacityCurent.capacity)
        {

          //  shipCapacityCurent.capacity = newCapacity;
            //shipCapacityCurent.weight = newWeight;
            //System.out.println("wlazl, tera wazymy: "+shipCapacityCurent.weight);
        }
        //else System.out.println("nie wlezie");

    }
}
