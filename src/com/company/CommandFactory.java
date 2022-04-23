package com.company;

import Exceptions.CommandDoesNotExists;
import Exceptions.CommandNotInCorrectFormat;
import Persistance.PersistanceStatics;
import Persistance.ShipPersistance;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class CommandFactory {

    String exitCommand = "exit";
    String loadCommand = "load ";
    String unloadCommand = "unload ";
    String showCommand = "show ";
    String deleteCommand = "delete ";
    String createCommand = "create ";
    String saveCommand = "save ";
    Pattern commandPattern = Pattern.compile(("(\\S+)"));

    public String[] GetAvailableCommands(){
        return new String[]{ exitCommand,loadCommand,saveCommand,showCommand,deleteCommand,createCommand, unloadCommand };
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
        else if(commandString.startsWith(unloadCommand)){
            ArrayList<String> parameters = new ArrayList<>();
            var matcher = commandPattern.matcher(commandString);
            while(matcher.find()){
                parameters.add(matcher.group(1));
            }
            return CreateUnloadCommand(parameters);
        }
        else if(commandString.startsWith(createCommand)){
            ArrayList<String> parameters = new ArrayList<>();
            var matcher = commandPattern.matcher(commandString);
            while(matcher.find()){
                parameters.add(matcher.group(1));
            }
            return CreateCreateCommand(parameters);
        }
        else if(commandString.startsWith(saveCommand)){
            ArrayList<String> parameters = new ArrayList<>();
            var matcher = commandPattern.matcher(commandString);
            while(matcher.find()){
                parameters.add(matcher.group(1));
            }
            return CreateSaveCommand(parameters);
        }
        else if(commandString.startsWith(exitCommand)){
            ArrayList<String> parameters = new ArrayList<>();
            var matcher = commandPattern.matcher(commandString);
            while(matcher.find()){
                parameters.add(matcher.group(1));
            }
            System.exit(2137);
            return null;
        }

        else return null;
    }

    private ICommand CreateShowCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "ships_id")){
            return new ShowAllShipsCommand();
        }
        else if(Objects.equals(parameters.get(1), "warehouses")) {
            return new ShowAllWarehousesCommand();
        }
        else if(Objects.equals(parameters.get(1), "id")){
            return new ShowShipCommand(parameters.get(2));
        }
        else if(Objects.equals(parameters.get(1), "warehouse")){
            return new ShowWarehouseCommand(parameters.get(2));
        }
        else{
            throw new CommandNotInCorrectFormat(showCommandInstructionShow);
        }
    }
    private ICommand CreateLoadCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "container")){
            return new LoadContainerCommand(parameters.get(2),parameters.get(3),parameters.get(4));
        }
        else if(Objects.equals(parameters.get(1), "all_containers")){
            return new LoadAllContainersCommand(parameters.get(2),parameters.get(3));
        }
        else{
            throw new CommandNotInCorrectFormat(showCommandInstructionLoad);
        }
    }
    private ICommand CreateUnloadCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "container")){
            return new UnloadContainerCommand(parameters.get(2),parameters.get(3),parameters.get(4));
        }
        else if(Objects.equals(parameters.get(1), "all_containers")){
            return new UnloadAllContainersCommand(parameters.get(2),parameters.get(3));
        }
        else{
            throw new CommandNotInCorrectFormat(showCommandInstructionUnload);
        }
    }
    private ICommand CreateSaveCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "save")){
            return new SaveCommand(parameters.get(2));
        }
        else if(Objects.equals(parameters.get(1), "restore")){
            return new RestoreCommand(parameters.get(2));
        }
        else{
            throw new CommandNotInCorrectFormat(showCommandInstructionSave);
        }
    }
    private ICommand CreateCreateCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "container")){
            return new CreateContainerCommand(parameters.get(2),parameters.get(3),parameters.get(0));

        }
        else if(Objects.equals(parameters.get(1), "ship")){
            return new CreateShipCommand(parameters.get(2),parameters.get(3),parameters.get(4),parameters.get(5),Integer.parseInt(parameters.get(6)),Double.parseDouble(parameters.get(7)),Integer.parseInt(parameters.get(8)),Integer.parseInt(parameters.get(9)),Integer.parseInt(parameters.get(10)));
        }
        else{
            throw new CommandNotInCorrectFormat(showCommandInstructionCreate);
        }
    }
    private ICommand CreateDeleteCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "container")){
            return new DeleteContainerCommand(parameters.get(2),parameters.get(3));
        }
        else{
            throw new CommandNotInCorrectFormat(showCommandInstructionDelete);
        }
    }

    private String showCommandInstructionShow = ("Argumenty komendy show: \n" +
            "\t ships_id\t - \twykaz id statkow \n" +
            "\t id {id}\t - \tstatek o podanym id \n" +
            "\t warehouses\t - \twykaz magazynów \n" +
            "\t warehouse {name}\t - \tstan podanego magazynu");
    private String showCommandInstructionLoad = ("Argumenty komendy load: \n" +
            "\tcontainer {container_id} {warehouse_name} {ship_id}\n" +
            "\tall_containers {warehouse_name} {ship_id}\n");
    private String showCommandInstructionUnload = ("Argumenty komendy unload: \n" +
            "\tcontainer {container_id} {ship_id} {warehouse_name}\n" +
            "\tall_containers {ship_id} {warehouse_name}\n");
    private String showCommandInstructionDelete = ("Argumenty komendy delete: \n" +
            "\tcontainer {container_id} {warehouse_name}\n");
    private String showCommandInstructionCreate = ("Argumenty komendy create: \n" +
            "\tcontainer {String_name} {String_homeport} {String_from} {String_destination} {int_capacity} {double_weight} {int_heavy} {int_electrified} {int_hazardous}\n");
    // tu trzeba dac coś
    private String showCommandInstructionSave = ("Argumenty komendy create: \n" +
            "\t\nsave {path}"+
            "\t\nsave"+
            "\t\nrevover {path}"+
            "\t\nrevover");



}



