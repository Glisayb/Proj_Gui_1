package Exceptions;

public class WarehouseNotFoundException extends Exception{

    public WarehouseNotFoundException
            (String name){

        super(String.format
                ("Warehouse  %s not found",
                        name));

    }

}