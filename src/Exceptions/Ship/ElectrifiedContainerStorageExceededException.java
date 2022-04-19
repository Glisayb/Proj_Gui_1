package Exceptions.Ship;

public class ElectrifiedContainerStorageExceededException extends Exception{

    public ElectrifiedContainerStorageExceededException(String id, String name, int capacity){

        super(String.format
                ("Container with id: %s would exceed '%s' ship electrified containers capacity of: %d",
                        id, name, capacity));

    }

}