package com.company;

import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseNotFoundException;

public interface ICommand {

    void execute() throws HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, WarehouseItemNotFoundException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException, WarehouseNotFoundException;
}
