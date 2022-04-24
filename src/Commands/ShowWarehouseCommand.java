package Commands;

import Models.Warehouse;
import Persistance.WarehousePersistance;
import com.company.Main;

import java.util.ArrayList;
import java.util.Objects;

public class ShowWarehouseCommand implements ICommand{

    private String name;

    public ShowWarehouseCommand(String name){

        this.name = name;
    }

    @Override
    public void execute() {

        var warehouse = Main.warehouses.stream().filter(s -> Objects.equals(s.name, name)).findFirst();
        if(warehouse.isPresent()){
            ArrayList<Warehouse> list = new ArrayList<>();
            list.add(warehouse.get());
            System.out.println(WarehousePersistance.Store.PrepareListOfWarehouses(list));
        }
    }
}