package Commands;

import Exceptions.ContainerNotFoundException;
import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import com.company.Main;

import java.util.Objects;

public class UnloadContainerCommand implements ICommand{

    private String containerId;
    private String id;
    private String warehouseName;

    public UnloadContainerCommand(String containerId, String id, String warehouseName){

        this.containerId = containerId;
        this.id = id;
        this.warehouseName = warehouseName;
    }

    @Override
    public void execute() throws HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, WarehouseItemNotFoundException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException, ContainerNotFoundException, WarehouseStorageCapacityExceededException {

        var ship = Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst();
        var warehouse = Main.warehouses.stream().filter(w -> Objects.equals(w.name, warehouseName)).findFirst();
        if(warehouse.isPresent() && ship.isPresent()){
            warehouse.get().storeInWarehouse(ship.get().unloadContainer(containerId));

        }
    }
}
