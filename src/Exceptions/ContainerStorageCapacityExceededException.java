package Exceptions;

public class ContainerStorageCapacityExceededException extends Exception{

    public ContainerStorageCapacityExceededException(){
        super();
    }
    public ContainerStorageCapacityExceededException(String reason){
        super(reason);
    }
}