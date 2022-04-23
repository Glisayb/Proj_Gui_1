package Commands;

        import Exceptions.Ship.*;
        import Exceptions.WarehouseItemNotFoundException;
        import Persistance.PersistanceStatics;
        import Persistance.ShipPersistance;
        import com.company.Main;

public class RestoreCommand implements ICommand{

    private String path;

    public RestoreCommand(String path){
        if (path.endsWith("txt"))
            this.path = path;
        else this.path = "ship.txt";

    }

    @Override
    public void execute() throws HazardousContainerStorageExceededException, ContainerStorageWeightExceededException, WarehouseItemNotFoundException, ContainerStorageCapacityExceededException, ElectrifiedContainerStorageExceededException, HeavyContainerStorageExceededException {

        var savedShips = PersistanceStatics.FilePersistance.Read(path);
        Main.ships = ShipPersistance.Store.CreateListOfShipsFromString(savedShips);

    }
}
