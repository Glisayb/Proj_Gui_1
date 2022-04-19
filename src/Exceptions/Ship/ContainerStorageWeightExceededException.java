package Exceptions.Ship;

public class ContainerStorageWeightExceededException extends Exception{

    public ContainerStorageWeightExceededException(String id, String name, double overweight){

        super(String.format
                ("Container with id: %s would exceed '%s' ship capacity by: %d",
                        id, name, overweight));

    }

}