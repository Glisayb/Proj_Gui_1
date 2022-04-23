package Models;

import Exceptions.ContainerNotFoundException;
import Exceptions.Ship.*;
import Models.Containers.ContBasic;
import Models.Containers.IContElectrified;
import Models.Containers.IContHazardous;
import Models.Containers.IContHeavy;
import com.company.Main;
import com.company.StaticClasses;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;


public class Ship {
    //basic info
    public String name;
    public String homeport;
    public String from;
    public String destination;

    //ID.
    public String shipId;

    //ship capacity
    public ArrayList<ContBasic> containerList;
    public ShipCapacityInfo shipCapacityMax;


    public Ship(String name, String homeport, String from, String destination, String shipId, ArrayList<ContBasic> containerList, ShipCapacityInfo shipCapacityMax) {
        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;
        this.shipId = shipId;
        this.containerList = containerList;
        this.shipCapacityMax = shipCapacityMax;
    }

    public Ship(String shipId, String name, String homeport, String from, String destination, ShipCapacityInfo shipCapacityMax){

        containerList = new ArrayList<ContBasic>(shipCapacityMax.capacity);
        this.shipId = shipId;

        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;

        this.shipCapacityMax = shipCapacityMax;
    }

    public Ship(String name, String homeport, String from, String destination, ShipCapacityInfo shipCapacityMax){

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
        if (shipCapacityMax.capacity <= contBasicCount) {
            throw new ContainerStorageCapacityExceededException(container.getId(), name, shipCapacityMax.capacity);
                    }

        //Czy starczy nośności
        double contBasicWeightCount = containerList.stream()
                .mapToDouble(ContBasic::getWeight)
                .sum()
                + container.getWeight();
        if (shipCapacityMax.weight <= contBasicWeightCount) {
            double overWeight =  contBasicWeightCount - shipCapacityMax.weight;
            throw new ContainerStorageWeightExceededException(container.getId(), name, overWeight);
        }

        //Sekcja sprawdzająca dostępność dla konkretnych typów
        {
            if (container instanceof IContHazardous) {
                long contToxicHazardousCount = containerList.stream().
                        filter(x -> x instanceof IContHazardous).count();
                if (shipCapacityMax.hazardous <= contToxicHazardousCount) {
                    throw new HazardousContainerStorageExceededException(container.getId(), name, shipCapacityMax.hazardous);
                }
            }

            if (container instanceof IContElectrified) {
                long contPowerGridCount = containerList.stream().
                        filter(x -> x instanceof IContElectrified).count();
                if (shipCapacityMax.electrified <= contPowerGridCount) {
                    throw new ElectrifiedContainerStorageExceededException(container.getId(), name, shipCapacityMax.electrified);
                }
            }

            if (container instanceof IContHeavy) {
                long contHeavyCount = containerList.stream().
                        filter(x -> x instanceof IContHeavy).count();
                if (shipCapacityMax.heavy <= contHeavyCount) {
                    throw new HeavyContainerStorageExceededException(container.getId(), name, shipCapacityMax.heavy);
                }
            }
        }
        containerList.add(container);
        }

        public ContBasic unloadContainer(String containerId) throws ContainerNotFoundException {
            var container = containerList.stream().filter(x -> Objects.equals(x.getId(), containerId)).findFirst();
            if(container.isPresent()){
                containerList.remove(container.get());
                return container.get();
            }
            else{
                throw new ContainerNotFoundException(containerId, shipId);
            }
        }


    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", homeport='" + homeport + '\'' +
                ", from='" + from + '\'' +
                ", destination='" + destination + '\'' +
                ", shipId='" + shipId + '\'' +
                '}';
    }
    public String getName() {
        return name;
    }
}


