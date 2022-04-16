package Exceptions;

public class ContainerNotFoundException extends Exception{
    public ContainerNotFoundException(String id, String name){

        super(String.format
                ("Container with id: %s not found on '%s' ship",
                        id, name));
    }
}
