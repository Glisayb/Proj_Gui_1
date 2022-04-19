package Models;

import Exceptions.Ship.*;
import Models.Containers.ContBasic;
import Models.Containers.IContElectrified;
import Models.Containers.IContHazardous;
import Models.Containers.IContHeavy;
import com.company.StaticClasses;

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
            throws
            ContainerStorageCapacityExceededException,
            ContainerStorageWeightExceededException,
            HazardousContainerStorageExceededException,
            HeavyContainerStorageExceededException,
            ElectrifiedContainerStorageExceededException {

        //Czy mamy miejsce
        long contBasicCount = containerList.size();
        if (shipCapacityMax.capacity >= contBasicCount) {
            throw new ContainerStorageCapacityExceededException(container.getId(), name, shipCapacityMax.capacity);
                    }

        //Czy starczy nośności
        double contBasicWeightCount = containerList.stream()
                .mapToDouble(ContBasic::getWeight)
                .sum()
                + container.getWeight();
        if (shipCapacityMax.weight >= contBasicWeightCount) {
            double overWeight =  contBasicWeightCount - shipCapacityMax.weight;
            throw new ContainerStorageWeightExceededException(container.getId(), name, overWeight);
        }

        //Sekcja sprawdzająca dostępność dla konkretnych typów
        {
            if (container instanceof IContHazardous) {
                long contToxicHazardousCount = containerList.stream().
                        filter(x -> x instanceof IContHazardous).count();
                if (shipCapacityMax.hazardous >= contToxicHazardousCount) {
                    throw new HazardousContainerStorageExceededException(container.getId(), name, shipCapacityMax.hazardous);
                }
            }

            if (container instanceof IContElectrified) {
                long contPowerGridCount = containerList.stream().
                        filter(x -> x instanceof IContElectrified).count();
                if (shipCapacityMax.electrified >= contPowerGridCount) {
                    throw new ElectrifiedContainerStorageExceededException(container.getId(), name, shipCapacityMax.electrified);
                }
            }

            if (container instanceof IContHeavy) {
                long contHeavyCount = containerList.stream().
                        filter(x -> x instanceof IContHeavy).count();
                if (shipCapacityMax.heavy >= contHeavyCount) {
                    throw new HeavyContainerStorageExceededException(container.getId(), name, shipCapacityMax.heavy);
                }
            }
        }
        //Ostatecznie gdy wszystkie powyzsze warunki przejda dodajemy kontener
        containerList.add(container);

        }
        public void farewell(){
            LocalTime time = LocalTime.now();
            System.out.println(name + " ETD :" + time +
                     "\n Farwell miss "+ name );
            String.format
                    ("%s ETD: %s \n Farwell miss  %s",
                            name,  time, name);
            //jak usunac statek
            //ship = null;
        }

    }


