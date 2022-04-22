package com.company;

import Models.Ship;
import Models.Warehouse;
import Persistance.ShipPersistance;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class LoadContainerCommand implements ICommand{

    private String containerId;
    private String id;
    private String warehouseName;

    public LoadContainerCommand(String warehouseName, String containerId, String id){

        this.containerId = containerId;
        this.id = id;
        this.warehouseName = warehouseName;
            }

    @Override
    public void execute() {

        var ship = Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst();
        var warehouse = Main.warehouses.stream().filter(w -> Objects.equals(w.name, warehouseName)).findFirst();
        warehouse.loadIntoShip(ship,containerId);
            System.out.println();
        }
    }
}