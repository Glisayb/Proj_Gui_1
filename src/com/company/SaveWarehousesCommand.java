package com.company;

import Commands.ICommand;
import Persistance.PersistanceStatics;
import Persistance.WarehousePersistance;

public class SaveWarehousesCommand implements ICommand{

    private String path;

    public SaveWarehousesCommand(String path){
        if (path.endsWith("txt"))
            this.path = path;
        else this.path = "warehouse.txt";

    }

    @Override
    public void execute(){
        PersistanceStatics.FilePersistance.WriteFile(path, WarehousePersistance.Store.PrepareListOfWarehouses(Main.warehouses));
        System.out.printf("Warehouses database saved - path : %s",path);

    }
}