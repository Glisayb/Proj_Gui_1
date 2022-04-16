package Models;

import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import Models.Containers.ContBasic;
import java.util.ArrayList;
import java.util.Optional;

public class Warehouse {

    String name;
    int capacity;

    public Warehouse(String name, int capacity){

        this.name = name;
        this.capacity = capacity;

    }
    ArrayList<WarehouseItem> warCollection = new ArrayList<WarehouseItem>();

    public WarehouseItem find(String id) throws WarehouseItemNotFoundException {

        Optional<WarehouseItem> any = warCollection.stream().filter(WarehouseItem -> id == WarehouseItem.getId()).findFirst();
            if (any.isPresent())
                return any.get();
            else throw new WarehouseItemNotFoundException(id);
                }

    public void storeInWarehouse(ContBasic container) throws WarehouseStorageCapacityExceededException {
        if (warCollection.size()<capacity) {
            warCollection.add(new WarehouseItem(container));
        }
        else throw new WarehouseStorageCapacityExceededException(container.getId(), capacity);
    }

    public ContBasic pickFromWarehouse(String id) throws WarehouseItemNotFoundException {
        WarehouseItem item = find(id);
        warCollection.remove(item);
        return (item.getContainer()) ;
    }

    public void loadIntoShip(Ship ship, String id) throws WarehouseItemNotFoundException {
        ship.loadContainer(pickFromWarehouse(id));
    }

    public void showCapacity(){
        System.out.println("Aktualny stan magazynu to: "+warCollection.size()+" z "+capacity);
    }

    public void showAll(){
        warCollection.stream().map(x -> x.getContainer().getId()).forEach(System.out::println);
    }

    public void loadAllIntoShip(Ship ship){
        int size = warCollection.size();
        for (int i = size-1; i>=0; i--){
            ship.loadContainer(warCollection.get(i).getContainer());
            warCollection.remove(i);
        }
    }

    public void loadIntoShipList(Ship ship, String[] list) throws WarehouseItemNotFoundException {
        for (int i = list.length - 1; i >= 0; i--) {
            loadIntoShip(ship, list[i]);
        }
    }
}
