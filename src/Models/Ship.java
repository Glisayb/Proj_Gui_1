package Models;

import Models.Containers.*;
import com.company.StaticClasses;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;


public class Ship {
    //basic info
    String name;
    String homeport;
    String from;
    String destination;

    //ID.
    private String shipId;

    //ship capacity
    ArrayList<ContBasic> containerList;
    ShipCapacityInfo shipCapacityMax;


    public Ship(String name,String homeport,String from,String destination, ShipCapacityInfo shipCapacityMax){

        containerList = new ArrayList<ContBasic>(shipCapacityMax.capacity);
        shipId = StaticClasses.IdGenerator.Generate();

        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;

        this.shipCapacityMax = shipCapacityMax;

    }

    public void loadContainer(ContBasic container)
            throws ArrayStoreException {

        if(container instanceof IContHazardous){
            long contToxicHazardousCount = containerList.stream().
                    filter(x -> x instanceof IContHazardous).count();
            if(shipCapacityMax.hazardous >= contToxicHazardousCount) {
                throw new ArrayStoreException("contHazardousCount over " + contToxicHazardousCount + container.toString());
            }
        }

        if(container instanceof IContElectrified){
            long contPowerGridCount = containerList.stream().
                    filter(x -> x instanceof IContElectrified).count();
            if (shipCapacityMax.electrified >= contPowerGridCount) {
                throw new ArrayStoreException("contElectrifiedCount over " + contPowerGridCount + container.toString());
            }
        }

        if(container instanceof IContHeavy){
            long contHeavyCount = containerList.stream().
                    filter(x -> x instanceof IContHeavy).count();
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

        }
        public void farwell(Ship ship, LocalTime time){
            System.out.println(new StringBuilder().append(ship.name).append(" ETD :").append(time).toString());
            System.out.println("Farwell miss "+ship.name);
            ship = null;

        }

    }


