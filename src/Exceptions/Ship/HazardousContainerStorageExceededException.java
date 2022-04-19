package Exceptions.Ship;

public class HazardousContainerStorageExceededException extends Exception{

    public HazardousContainerStorageExceededException(String id, String name, int capacity){

        super(String.format
                ("Container with id: %s would exceed '%s' ship hazzardous containers capacity of: %d",
                        id, name, capacity));

    }

}