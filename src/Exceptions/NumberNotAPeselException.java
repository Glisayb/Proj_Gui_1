package Exceptions;

public class NumberNotAPeselException extends Exception{

    public NumberNotAPeselException
            (int digits){

        super(String.format
                ("Given number is nor a pesel, pesel have 11digits, that number had %d",
                        digits));

    }

}
