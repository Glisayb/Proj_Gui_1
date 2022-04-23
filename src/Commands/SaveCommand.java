package Commands;

import Exceptions.Ship.*;
import Exceptions.WarehouseItemNotFoundException;
import Persistance.PersistanceStatics;
import Persistance.ShipPersistance;
import com.company.Main;

public class SaveCommand implements ICommand{

    private String path;

    public SaveCommand(String path){
        if (path.endsWith("txt"))
            this.path = path;
        else this.path = "ship.txt";

    }

    @Override
    public void execute() throws HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, WarehouseItemNotFoundException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException {

        PersistanceStatics.FilePersistance.WriteFile(path, ShipPersistance.Store.PrepareListOfShips(Main.ships));

    }
}
