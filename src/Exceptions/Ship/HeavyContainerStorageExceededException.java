package Exceptions.Ship;

public class HeavyContainerStorageExceededException extends Exception{

    public HeavyContainerStorageExceededException(String id, String name, int capacity){

        super(String.format
                ("Container with id: %s would exceed '%s' ship heavy containers capacity of: %d",
                        id, name, capacity));

    }

}