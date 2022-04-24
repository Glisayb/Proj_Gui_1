package Commands;

import Exceptions.ContainerNotFoundException;
import Models.Containers.ContBasic;
import com.company.Main;
import com.company.StaticClasses;

import java.util.Objects;

public class UnloadAllContainersOnTrainCommand implements ICommand {
    String id;

    public UnloadAllContainersOnTrainCommand(String id) {

        this.id = id;
    }

    @Override
    public void execute() throws ContainerNotFoundException {
        var ship = Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst().get();
        var list = ship.containerList;
        for (ContBasic container : list) {
            ship.containerList.remove(container);
            StaticClasses.loadingTrain(container);
        }
    }
}