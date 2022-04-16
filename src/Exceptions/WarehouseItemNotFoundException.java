package Exceptions;

public class WarehouseItemNotFoundException extends Exception{
    public WarehouseItemNotFoundException(String id){
        super("Warehouse item with id " + id +" not found");
    }

}
