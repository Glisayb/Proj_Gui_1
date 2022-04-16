package Exceptions;

public class WarehouseStorageCapacityExceededException extends Exception{

    public WarehouseStorageCapacityExceededException(String id, int capacity){

        super("Warehouse item with id " + id +"would exceed warehouse capacity of: " + capacity);
    }

}
