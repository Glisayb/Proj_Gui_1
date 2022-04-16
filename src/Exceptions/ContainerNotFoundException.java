package Exceptions;

public class ContainerNotFoundException extends Exception{
    public ContainerNotFoundException(String id){
        super("Container with id " + id +" not found");
    }

}
