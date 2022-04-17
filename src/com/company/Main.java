package com.company;

import Exceptions.ContainerStorageCapacityExceededException;
import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import Models.*;
import Models.Containers.ContBasic;
import Models.*;
import Models.Containers.ContBasic;
import Models.Containers.ContHeavy;
import Models.Containers.ContToxicLiquid;
import Models.Containers.IContHeavy;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args)
            throws WarehouseStorageCapacityExceededException, WarehouseItemNotFoundException {

        ShipCapacityInfo intel1 = new ShipCapacityInfo(44,21.37,10,7,13);
        Ship latajacy = new Ship("latajacyholender","Amsterdam","Borholm","Danzig", intel1);
        Warehouse WZ =new Warehouse("wschodniozachodni", 3);
        ContHeavy grubas = new ContHeavy(22,44,200);
        ContToxicLiquid bimber = new ContToxicLiquid( 121,33,90900,92.7, Pollutions.ALKOHOLIC,"Skittlesowka");
        System.out.println(bimber.getDensity());

        WZ.storeInWarehouse(new ContBasic(142,223));

        new WarehouseItem(grubas, LocalDate.now());

        WZ.showAll();
        WZ.showCapacity();
        WZ.loadAllIntoShip(latajacy);
        WZ.showCapacity();
        String[] krypy = new String[2];
        krypy[0] = bimber.getId();
        krypy[1] = grubas.getId();
        WZ.storeInWarehouse(grubas);
        WZ.storeInWarehouse(bimber);
        WZ.loadIntoShipList(latajacy, krypy);
        WZ.showAll();

    }
}
