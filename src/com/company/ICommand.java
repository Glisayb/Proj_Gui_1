package com.company;

import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;

public interface ICommand {

    void execute() throws HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, WarehouseItemNotFoundException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException;
}
