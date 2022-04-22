package Models;

import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import Models.Containers.*;
import com.company.StaticClasses;

import java.util.ArrayList;
import java.util.Optional;

public class Warehouse {

    public String name;
    public int capacity;

    public Warehouse(String name, int capacity){

        this.name = name;
        this.capacity = capacity;
    }

    public ArrayList<WarehouseItem> warCollection = new ArrayList<>();

    public WarehouseItem getItemById(String id)
            throws WarehouseItemNotFoundException {

        Optional<WarehouseItem> any = warCollection.stream().
                filter(WarehouseItem -> id == WarehouseItem.getId()).findFirst();
        if (any.isPresent())
            return any.get();
        else
            throw new WarehouseItemNotFoundException
                    (id, name);
    }

    public static StorageDaysLimit DetrmineHowLongContainerCanBeStored(ContBasic container){
        StorageDaysLimit limit;
        if(container instanceof IContToxicLoose)
            limit = StorageDaysLimit.ContToxicLoose;
        else if(container instanceof IContToxicLiquid)
            limit = StorageDaysLimit.ContToxicLiquid;
        else if(container instanceof IContExplosive)
            limit = StorageDaysLimit.ContExplosive;
        else
            limit = StorageDaysLimit.None;
        return limit;
    }

    public void storeInWarehouse(ContBasic container) throws WarehouseStorageCapacityExceededException {
        StorageDaysLimit limit = DetrmineHowLongContainerCanBeStored(container);
        if (warCollection.size()<capacity) {
            warCollection.add(new WarehouseItem(container, StaticClasses.Timer.date, StorageDaysLimit.ContExplosive));
        }
        else
            throw new WarehouseStorageCapacityExceededException(container.getId(), name, capacity);
    }

    public ContBasic pickFromWarehouse(String id) throws WarehouseItemNotFoundException {

        WarehouseItem item = getItemById(id);
        warCollection.remove(item);
        return (item.getContainer());
    }

    public void loadIntoShip(Ship ship, String id) throws WarehouseItemNotFoundException, HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException {

        ship.loadContainer(pickFromWarehouse(id));
        System.out.println(id+" loaded into : "+ ship.getName()+" ship");
    }

    public void showCapacity(){
        System.out.printf("Actual '%s' warehouse capacity is: %d of all possible %d warehouse capacity%n",
                name, warCollection.size(), capacity);
    }

    public void showAll(){

        warCollection.stream().
                map(x -> x.getContainer().getId()).
                forEach(System.out::println);
    }

    public void loadAllIntoShip(Ship ship) throws HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException {
        int size = warCollection.size();

        for (int i = size-1; i>=0; i--){
            ship.loadContainer(warCollection.get(i).getContainer());
            warCollection.remove(i);
        }
        System.out.println(size+" containers loaded into : "+ ship.getName()+" ship");
    }

    public void loadIntoShipList(Ship ship, String[] list)
            throws WarehouseItemNotFoundException, HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException {

        for (String id : list) {
            loadIntoShip(ship, id);
        }
    }

}
