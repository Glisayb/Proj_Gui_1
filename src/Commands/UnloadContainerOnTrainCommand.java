package Commands;

import Commands.ICommand;
import Exceptions.ContainerNotFoundException;
import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import Models.Containers.ContBasic;
import Models.Ship;
import com.company.Main;
import com.company.StaticClasses;
import com.company.Train;

import java.util.Objects;

public class UnloadContainerOnTrainCommand implements ICommand {
    String containerId;
    String id;

    public UnloadContainerOnTrainCommand(String containerId, String id, String warehouseName){

        this.containerId = containerId;
        this.id = id;
    }
    public void loadTrain(ContBasic container){
        Main.shipsIntoTrain.add(container);
        run();
    }

    @Override
    public void execute() throws ContainerNotFoundException {
        var ship = Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst();
        var container = ship.get().unloadContainer(containerId);
        }
    }
