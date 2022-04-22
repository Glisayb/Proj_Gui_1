package com.company;

import Exceptions.CommandDoesNotExists;
import Exceptions.CommandNotInCorrectFormat;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class CommandFactory {

    String exitCommand = "exit ";
    String loadCommand = "load ";
    String showCommand = "show ";
    String deleteCommand = "delete ";
    String createCommand = "create ";
    String saveCommand = "save ";
    Pattern commandPattern = Pattern.compile(("(\\S+)"));

    public String[] GetAvailableCommands(){
        return new String[]{ exitCommand,loadCommand,saveCommand,showCommand,deleteCommand,createCommand };
    }
    public ICommand GetCommand(String commandString) throws CommandNotInCorrectFormat, CommandDoesNotExists {

        if(commandString.startsWith(showCommand)){
            ArrayList<String> parameters = new ArrayList<>();
            var matcher = commandPattern.matcher(commandString);
            while(matcher.find()){
                parameters.add(matcher.group(1));
            }
            return CreateShowCommand(parameters);
        }

        else if(commandString.startsWith(loadCommand)){
            ArrayList<String> parameters = new ArrayList<>();
            var matcher = commandPattern.matcher(commandString);
            while(matcher.find()){
                parameters.add(matcher.group(1));
            }
            return CreateLoadCommand(parameters);
        }

        else return null;
    }

    private ICommand CreateShowCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "ships_id")){
            return new ShowAllShipsCommand();
        }
        else if(Objects.equals(parameters.get(1), "id")){
            return new ShowShipCommand(parameters.get(2));
        }
        else{
            throw new CommandNotInCorrectFormat(showCommandInstructionShip);
        }
    }
    private ICommand CreateLoadCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "id")){
            return new LoadContainerCommand("Zachodni",parameters.get(2),parameters.get(3));
        }
        else if(Objects.equals(parameters.get(1), "id")){
            return new ShowShipCommand(parameters.get(2));
        }
        else{
            throw new CommandNotInCorrectFormat(showCommandInstructionShip);
        }
    }

    private String showCommandInstructionShip = "Komenda show jako pierwszy argument przyjmuje słowo 'ships_id'";
}




