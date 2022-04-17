package Exceptions;

public class WarehouseItemNotFoundException extends Exception{

    public WarehouseItemNotFoundException
            (String id, String name){

        super(String.format
                ("Warehouse item with id: %s not found on '%s' ship",
                        id, name));

    }

}
