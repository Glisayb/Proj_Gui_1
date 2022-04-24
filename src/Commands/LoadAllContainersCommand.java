package Commands;

import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;
import com.company.Main;

import java.util.Objects;

public class LoadAllContainersCommand implements ICommand{

    private String id;
    private String warehouseName;

    public LoadAllContainersCommand(String warehouseName, String id){

        this.id = id;
        this.warehouseName = warehouseName;
    }

    @Override
    public void execute() throws HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, WarehouseItemNotFoundException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException {

        var ship = Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst();
        var warehouse = Main.warehouses.stream().filter(w -> Objects.equals(w.name, warehouseName)).findFirst();
        if(warehouse.isPresent() && ship.isPresent()){
            warehouse.get().loadAllIntoShip(ship.get());

        }
    }
}

