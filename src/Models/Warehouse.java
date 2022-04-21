package Models;

import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import Models.Containers.ContBasic;
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

    public void storeInWarehouse(ContBasic container)
            throws WarehouseStorageCapacityExceededException {
        if (warCollection.size()<capacity) {
            warCollection.add(new WarehouseItem(container));
        }
        else
            throw new WarehouseStorageCapacityExceededException
                    (container.getId(), name, capacity);
    }

    public ContBasic pickFromWarehouse(String id)
            throws WarehouseItemNotFoundException {

        WarehouseItem item = getItemById(id);
        warCollection.remove(item);
        return (item.getContainer());
    }

    public void loadIntoShip(Ship ship, String id)
            throws WarehouseItemNotFoundException, HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException {

        ship.loadContainer(pickFromWarehouse(id));
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
    }

    public void loadIntoShipList(Ship ship, String[] list)
            throws WarehouseItemNotFoundException, HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException {

        for (String id : list) {
            loadIntoShip(ship, id);
        }
    }

}
