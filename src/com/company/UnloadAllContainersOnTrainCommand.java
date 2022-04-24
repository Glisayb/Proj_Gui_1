package com.company;

import Commands.ICommand;
import Exceptions.ContainerNotFoundException;
import Models.Containers.ContBasic;

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