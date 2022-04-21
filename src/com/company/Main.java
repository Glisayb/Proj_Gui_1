package com.company;

import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import Models.*;
import Models.Containers.*;
import Persistance.PersistanceStatics;
import Persistance.ShipPersistance;
import Persistance.WarehousePersistance;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse("Wschodni", 122);

        ShipCapacityInfo intel1 = new ShipCapacityInfo(4224, 2137, 10, 7, 13);
        ShipCapacityInfo intel2 = new ShipCapacityInfo(1245, 1236, 3, 1, 7);

        Ship zenobia = new Ship("Zenobia", "Nowy York", "Lodz", "London", intel2);
        Ship amanda = new Ship("Amanda", "Amsterdam", "Borholm", "Danzig", intel1);

        ContBasic basic = new ContBasic(321.34, 12);
        ContHeavy heavy = new ContHeavy(222, 44, 200);
        ContCooling cooling = new ContCooling(222, 44, 200, 33.4);
        ContExplosive explosive = new ContExplosive(421.11, 87, 90090, 150);
        ContToxicLiquid toxicLiquid = new ContToxicLiquid(121, 33, 90900, 92.7, Pollutions.ALKOHOLIC, "Skittlesowka");
        ContToxicLoose toxicLoose = new ContToxicLoose(143.21, 42, 5555, Pollutions.STUPIDITY, true);

        Date nowDate = new Date();
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(dataFormat.format(nowDate));
        System.out.println(LocalDate.now());


        try {
            Boolean read = false;
            Boolean write = false;
            var path = "ship.txt";
            if (write) {
                zenobia.loadContainer(basic);
                zenobia.loadContainer(heavy);
                zenobia.loadContainer(toxicLiquid);

                amanda.loadContainer(toxicLiquid);
                amanda.loadContainer(cooling);
                amanda.loadContainer(explosive);

                ArrayList<Ship> ships = new ArrayList<>();
                ships.add(zenobia);
                ships.add(amanda);
                var shipsAsString = ShipPersistance.Store.PrepareListOfShips(ships);
                PersistanceStatics.FilePersistance.WriteFile(path, shipsAsString);
            }
            if (read) {
                var shipsAsString = PersistanceStatics.FilePersistance.Read(path);
                var shipsLoaded = ShipPersistance.Store.CreateListOfShipsFromString(shipsAsString);
                System.out.println(shipsLoaded);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try {
            Boolean read = true;
            Boolean write = false;
            var path = "warehouse.txt";
            if (write) {
                warehouse.storeInWarehouse(heavy);
                warehouse.storeInWarehouse(toxicLiquid);
                warehouse.storeInWarehouse(basic);

                var warehouseAsString = WarehousePersistance.Store.PrepareToSave(warehouse);
                PersistanceStatics.FilePersistance.WriteFile(path, warehouseAsString);
            }
            if (read) {
                var warehouseAsString = PersistanceStatics.FilePersistance.Read(path);
                var warehouseLoaded = WarehousePersistance.Store.CreateWarehouseFromString(warehouseAsString);
                warehouseLoaded.showAll();
            }

        } catch (Exception ee) {
            System.out.println(ee.toString());

        }
    }
}