package com.company;

import Commands.*;
import Exceptions.CommandDoesNotExists;
import Exceptions.CommandNotInCorrectFormat;

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
    String castOffCommand = "cast_off ";
    Pattern commandPattern = Pattern.compile(("(\\S+)"));

    public String[] GetAvailableCommands(){
        return new String[]{ showCommand, loadCommand, unloadCommand, createCommand, deleteCommand, saveCommand, castOffCommand, exitCommand };
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
            parameters.add(commandString);
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
        else if(commandString.startsWith(castOffCommand)){
            ArrayList<String> parameters = new ArrayList<>();
            var matcher = commandPattern.matcher(commandString);
            while(matcher.find()){
                parameters.add(matcher.group(1));
            }
            return CreateCastOffCommand(parameters);
        }
        else if(commandString.startsWith(deleteCommand)){
            ArrayList<String> parameters = new ArrayList<>();
            var matcher = commandPattern.matcher(commandString);
            while(matcher.find()){
                parameters.add(matcher.group(1));
            }
            return CreateDeleteCommand(parameters);
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
        if(Objects.equals(parameters.get(1), "container"))
            if(Objects.equals(parameters.get(2), "into_warehouse")) {
                return new UnloadContainerCommand(parameters.get(3), parameters.get(4), parameters.get(5));
            }
            else if(Objects.equals(parameters.get(2), "on_train")) {
                return new UnloadContainerOnTrainCommand(parameters.get(3), parameters.get(4));
            }
            else if(Objects.equals(parameters.get(1), "all_containers"))
                if(Objects.equals(parameters.get(2), "into_warehouse")) {
                    return new UnloadAllContainersCommand(parameters.get(3), parameters.get(4));
            }
            else if(Objects.equals(parameters.get(2), "on_train")) {
                return new UnloadAllContainersOnTrainCommand(parameters.get(3));
            }
            else throw new CommandNotInCorrectFormat(showCommandInstructionUnload);

    }
    private ICommand CreateCastOffCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if (Objects.equals(parameters.get(1), "ship")) {
            return new CastOffCommand(parameters.get(2));
        } else if (Objects.equals(parameters.get(1), "all_ships"))
            return new CastOffAllCommand();
        else throw new CommandNotInCorrectFormat(showCommandInstructionCastOff);

    }
       private ICommand CreateSaveCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(1), "save")){
            if (Objects.equals(parameters.get(2), "ships")) {
                return new SaveCommand(parameters.get(3));
            }
            else if (Objects.equals(parameters.get(2), "warehouses"))
                return new SaveWarehousesCommand(parameters.get(3));}
        else if(Objects.equals(parameters.get(1), "restore"))
        {
            if (Objects.equals(parameters.get(2), "ships")) {
                return new RestoreCommand(parameters.get(3));
            }
            else if (Objects.equals(parameters.get(2), "warehouses"))
                return new RestoreWarehousesCommand(parameters.get(3));
        }
        else throw new CommandNotInCorrectFormat(showCommandInstructionSave);
        return null;
    }
    private ICommand CreateCreateCommand(ArrayList<String> parameters) throws CommandNotInCorrectFormat {
        if(Objects.equals(parameters.get(2), "container")){
            return new CreateContainerCommand(parameters.get(3),parameters.get(4),parameters.get(0));
        }
        else if(Objects.equals(parameters.get(2), "ship")){
            return new CreateShipCommand(parameters.get(3),parameters.get(4),parameters.get(5),parameters.get(6),Integer.parseInt(parameters.get(7)),Double.parseDouble(parameters.get(8)),Integer.parseInt(parameters.get(9)),Integer.parseInt(parameters.get(10)),Integer.parseInt(parameters.get(11)));
        }
        else if(Objects.equals(parameters.get(2), "warehouse")){
            return new CreateWarehouse(parameters.get(2),Integer.parseInt(parameters.get(3)));
        }
        else if(Objects.equals(parameters.get(2), "info")){
            throw new CommandNotInCorrectFormat(showCommandInstructionCreate);
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
            "\tcontainer into_warehouse {container_id} {ship_id} {warehouse_name}\n" +
            "\tcontainer on_train {container_id} \n" +
            "\tall_containers into_warehouse {ship_id} {warehouse_name}\n"+
            "\tall_containers on_train {ship_id}\n");
    private String showCommandInstructionDelete = ("Argumenty komendy delete: \n" +
            "\tcontainer {container_id} {warehouse_name}\n");
    private String showCommandInstructionCastOff = ("Argumenty komendy cast_off: \n" +
            "\tship {ship_id}\n");
    private String showCommandInstructionCreate = ("Argumenty komendy create: \n" +
            "\twarehouse {String_name} {int_capacity}\n"+
            "\tship {String_name} {String_homeport} {String_from} {String_destination} {int_capacity} {double_weight} {int_heavy} {int_electrified} {int_hazardous}\n"+
            "\tcontainer {warehouse} Zwykły Wysokość ubezpieczenia {i} Waga kontenera {d}\n"+
            "\tcontainer {warehouse} Ciężki Wysokość ubezpieczenia {i} Waga kontenera {d} Iso {i}\n"+
            "\tcontainer {warehouse} Płynny Wysokość ubezpieczenia {i} Waga kontenera {d} Gęstość {d}\n"+
            "\tcontainer {warehouse} Lodówka Wysokość ubezpieczenia {i} Waga kontenera {d} Iso {i} Pobór prundu {d}\n"+
            "\tcontainer {warehouse} Wybuchowy Wysokość ubezpieczenia {i} Waga kontenera {d} Iso {i} Zasięg rażenia {d}\n"+
            "\tcontainer {warehouse} Toksyczny płynny Wysokość ubezpieczenia {i} Waga kontenera {d} Iso {i} Nazwa płynu {s} Gęstość {d} Rodzaj niebezpieczeństwa {BIOHAZZARD}\n"+
            "\tcontainer {warehouse} Toksyczny sypki Wysokość ubezpieczenia {i} Waga kontenera {d} Iso {i} Niebezpieczeństwo trujących oparów {tak/nie} Rodzaj niebezpieczeństwa {BIOHAZZARD}\n");
    private String showCommandInstructionSave = ("Argumenty komendy save: \n" +
            "\t\nsave ships {path.txt}"+
            "\t\nrestore ships {path.txt}"+
            "\t\nsave warehouses {path.txt}"+
            "\t\nrestore warehouses {path.txt}");

}




