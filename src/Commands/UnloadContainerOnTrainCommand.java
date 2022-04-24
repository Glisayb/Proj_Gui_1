package Commands;

import Exceptions.ContainerNotFoundException;
import com.company.Main;
import com.company.StaticClasses;

import java.util.Objects;

public class UnloadContainerOnTrainCommand implements ICommand {
    String containerId;
    String id;

    public UnloadContainerOnTrainCommand(String containerId, String id){

        this.containerId = containerId;
        this.id = id;
    }

    @Override
    public void execute() throws ContainerNotFoundException {
        var ship = Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst();
        var container = ship.get().unloadContainer(containerId);
        StaticClasses.loadingTrain(container);
        }
    }
