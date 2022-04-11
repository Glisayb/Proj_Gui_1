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
    ArrayList<WarehouseItem> warCollection = new ArrayList<WarehouseItem>(capacity);

    public void storeInWarehouse(ContBasic container) throws ArrayStoreException {
        if (warCollection.size()<capacity) {
            warCollection.add(new WarehouseItem(container));
        }
        else throw new ArrayStoreException("Warehouse (mocno) FULL");
    }

}
