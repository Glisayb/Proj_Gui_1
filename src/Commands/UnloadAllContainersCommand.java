package Commands;

import Exceptions.ContainerNotFoundException;
import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import com.company.Main;

import java.util.Objects;

public class UnloadAllContainersCommand implements ICommand{

    private String containerId;
    private String id;
    private String warehouseName;

    public UnloadAllContainersCommand(String id, String warehouseName){

        this.id = id;
        this.warehouseName = warehouseName;

    }

    @Override
    public void execute() throws HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, WarehouseItemNotFoundException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException, ContainerNotFoundException, WarehouseStorageCapacityExceededException {

        var warehouse = Main.warehouses.stream().filter(w -> Objects.equals(w.name, warehouseName)).findFirst();
        var list = Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst().get().containerList;
        if(warehouse.isPresent()){
            while(list.size()>0){
                warehouse.get().storeInWarehouse(list.get(list.size()-1));
                list.remove(list.size()-1);
            }
        }
    }
}
