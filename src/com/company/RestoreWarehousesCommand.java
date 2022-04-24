package com.company;

import Commands.ICommand;
import Persistance.PersistanceStatics;
import Persistance.WarehousePersistance;

public class RestoreWarehousesCommand implements ICommand{

    private String path;

    public RestoreWarehousesCommand(String path){
        if (path.endsWith("txt"))
            this.path = path;
        else this.path = "warehouse.txt";

    }

    @Override
    public void execute() {
        var savedWarehouses = PersistanceStatics.FilePersistance.Read(path);
        Main.warehouses = WarehousePersistance.Store.CreateListOfWarehousesFromString(savedWarehouses);
        System.out.printf("Warehouses database restored - from path : %s",path);

    }
}