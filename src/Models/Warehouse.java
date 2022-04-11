package Models;

import Models.Containers.ContBasic;
import java.util.ArrayList;

public class Warehouse {

    String name;
    int capacity;

    public Warehouse(String name, int capacity){

        this.name = name;
        this.capacity = capacity;

    }
    ArrayList<WarehouseItem> warCollection = new ArrayList<WarehouseItem>();

    public void storeInWarehouse(ContBasic container) throws ArrayStoreException {
        if (warCollection.size()<capacity) {
            warCollection.add(new WarehouseItem(container));
        }
        else throw new ArrayStoreException("Warehouse (mocno) FULL");
    }
    public ContBasic pickFromWarehouse(ContBasic container) throws IllegalArgumentException {
        int n = warCollection.size();
        warCollection.remove(container);
        if (n == warCollection.size()) {
            throw new ArrayStoreException(container.getId()+" Nie ma takiego w magazynie");
        }
        return (container);

    }
    public void fromWarehouseIntoShipZaur(Ship ship, ContBasic container){
        ship.loadContainer(pickFromWarehouse(container));
    }

    public void showCapacity(){
        System.out.println("Aktualny stan magazynu to: "+warCollection.size()+" z "+capacity);
    }

    public void showAll(){
        warCollection.stream().map(x -> x.container.getId()).forEach(System.out::print);
        }

    public void annihilateCont(ContBasic container){
        pickFromWarehouse(container);
    }

}
