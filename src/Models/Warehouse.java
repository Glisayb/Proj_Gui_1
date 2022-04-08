package Models;


import Models.Containers.ContBasic;

import java.util.LinkedHashSet;

public class Warehouse {

    String name;
    int capacity;
    int stored;

    public Warehouse(String name, int capacity){

        this.name = name;
        this.capacity = capacity;

    }
    LinkedHashSet<WarehouseItem> warCollection = new LinkedHashSet<>();

    public void storeInWarehouse(ContBasic container){
        if (stored<capacity) {

            warCollection.add(new WarehouseItem(container));
            stored++;

        }
        else System.out.println("Magazyn przepelnion");
    }

}
