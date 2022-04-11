package Models;

import Models.Containers.*;
import com.company.StaticClasses;

import java.util.ArrayList;


public class Ship {
    //basic info
    String name;
    String homeport;
    String from;
    String destination;

    //ID.
    String shipId;

    //ship capacity
    ArrayList<ContBasic> containerList;
    ShipCapacityInfo shipCapacityMax;
    //ShipCapacityInfo shipCapacityCurent;

    public Ship(String name,String homeport,String from,String destination, ShipCapacityInfo shipCapacityMax){

        containerList = new ArrayList<ContBasic>(shipCapacityMax.capacity);
        shipId = StaticClasses.IdGenerator.Generate();

        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;

        this.shipCapacityMax = shipCapacityMax;
        //this.shipCapacityCurent = shipCapacityCurent;

    }

    public void loadContainer(ContBasic container) throws ArrayStoreException {
/*      int newCapacity;
        double newWeight;

        if(container instanceof ContHeavy){
            var contToxicLiquidCount = containerList.stream().filter(x -> x instanceof ContHeavy).count();
            if(shipCapacityMax.heavy < contToxicLiquidCount ){
                containerList.add(container);
            }
        }*/

        if(container instanceof IContHazardous){
            long contToxicHazardousCount = containerList.stream().filter(x -> x instanceof IContHazardous).count();
            if(shipCapacityMax.hazardous >= contToxicHazardousCount) {
                throw new ArrayStoreException("contHazardousCount over " + contToxicHazardousCount + container.toString());
            }
        }

        if(container instanceof IContElectrified){
            long contPowerGridCount = containerList.stream().filter(x -> x instanceof IContElectrified).count();
            if (shipCapacityMax.electrified >= contPowerGridCount) {
                throw new ArrayStoreException("contElectrifiedCount over " + contPowerGridCount + container.toString());
            }
        }

        if(container instanceof IContHeavy){
            long contHeavyCount = containerList.stream().filter(x -> x instanceof IContHeavy).count();
            if (shipCapacityMax.heavy >= contHeavyCount) {
                throw new ArrayStoreException("contToxicCount over " + contHeavyCount + container.toString());
            }
        }

                    double contBasicWeightCount = containerList.stream()
                    .mapToDouble(ContBasic::getWeight)
                    .sum();
                    if (shipCapacityMax.weight >= contBasicWeightCount) {
                        throw new ArrayStoreException("contBasicWeightCount over " + contBasicWeightCount + container.toString());
            }

                    long contBasicCount = containerList.size();
                    if (shipCapacityMax.capacity >= contBasicCount) {
                        throw new ArrayStoreException("contBasicCount over " + contBasicCount + container.toString());
            }

            //basic current capacity info
            containerList.add(container);
                /*
            shipCapacityCurent.capacity++;
            shipCapacityCurent.weight=+container.getWeight();


             //special current capacity info
        if(container instanceof IContHeavy)shipCapacityCurent.heavy++;
        if(container instanceof IContElectrified)shipCapacityCurent.electrified++;
        if(container instanceof IContHazardous)shipCapacityCurent.hazardous++;
                */

                /*
                newCapacity = shipCapacityCurent.capacity + 1;
        newWeight = shipCapacityCurent.weight + container.getWeight();

        if (newWeight <= shipCapacityMax.weight && newCapacity <= shipCapacityCurent.capacity){

            shipCapacityCurent.capacity = newCapacity;
            shipCapacityCurent.weight = newWeight;
            System.out.println("wlazl, tera wazymy: "+shipCapacityCurent.weight);
        }
        else System.out.println("nie wlezie");
                */

        }

    }


