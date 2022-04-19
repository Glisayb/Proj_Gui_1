package Exceptions.Ship;

public class ContainerStorageCapacityExceededException extends Exception{

    public ContainerStorageCapacityExceededException(String id, String name, int capacity){

        super(String.format
                ("Container with id: %s would exceed '%s' ship capacity of: %d",
                        id, name, capacity));

    }

}