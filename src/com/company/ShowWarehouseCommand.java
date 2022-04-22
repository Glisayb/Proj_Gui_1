package com.company;

import Models.Ship;
import Persistance.ShipPersistance;

import java.sql.Array;
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

        }
    }
}