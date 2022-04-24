package Commands;


import Models.Warehouse;
import com.company.Main;

public class CreateWarehouse implements ICommand {

    public String name;
    public int capacity;

    public CreateWarehouse(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public void execute() {

        Warehouse warehouse = new Warehouse(name,capacity);
        Main.warehouses.add(warehouse);
        System.out.printf("Warehouse '%s' created with total capacity of : %d%n", name,capacity);

    }
}
