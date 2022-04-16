package Exceptions;

public class WarehouseStorageCapacityExceededException extends Exception{

    public WarehouseStorageCapacityExceededException
            (String id, String name, int capacity){

        super(String.format
                ("Warehouse item with id: %s would exceed '%s' warehouse capacity of: %d",
                        id, name, capacity));

    }

}
