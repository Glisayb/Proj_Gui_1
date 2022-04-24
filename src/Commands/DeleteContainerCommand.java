package Commands;

import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseNotFoundException;
import com.company.Main;

import java.util.Objects;

public class DeleteContainerCommand implements ICommand {

    private String containerId;
    private String warehouseName;

    public DeleteContainerCommand(String containerId, String warehouseName){

        this.containerId = containerId;
        this.warehouseName = warehouseName;
    }

    @Override
    public void execute() throws WarehouseNotFoundException, WarehouseItemNotFoundException {

        var warehouse = Main.warehouses.stream().filter(w -> Objects.equals(w.name, warehouseName)).findFirst();
        var warehouseIteam = warehouse.get().warCollection.stream().filter(wi -> Objects.equals(wi.getId(),containerId)).findFirst();
        // czemu tu jest always trueeeeee?
        if(!warehouse.isPresent())
        throw new WarehouseNotFoundException(warehouseName);
        if(!warehouseIteam.isPresent())
        throw new WarehouseItemNotFoundException(containerId, warehouseName);
        else {
            warehouse.get().warCollection.remove(warehouseIteam.get());
            System.out.println("Kontener : "+containerId+" usunięty z magazyny : "+warehouseName);
        }
    }
}