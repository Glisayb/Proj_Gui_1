package com.company;

import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import Models.*;
import Models.Containers.*;
import Persistance.PersistanceStatics;
import Persistance.ShipPersistance;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    private static String shipsFilePath = "ship.txt";
    public static ArrayList<Ship> ships;
    public static ArrayList<Warehouse> warehouses;
    public static void main(String[] args) {

        if (!PersistanceStatics.FilePersistance.FileExists(shipsFilePath)) {
            ships = GenerateShips();
            warehouses = GenerateWarehouses();

        } else {
            var savedShips = PersistanceStatics.FilePersistance.Read(shipsFilePath);
            ships = ShipPersistance.Store.CreateListOfShipsFromString(savedShips);
        }

        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(() -> {
            while (true) {
                Thread.sleep(5000);
                StaticClasses.Timer.date = StaticClasses.Timer.date.plusDays(1);
            }
        });
        service.submit(new Menu());
    }

    private static ArrayList<Warehouse> GenerateWarehouses()
    {
        var warehouses = new ArrayList<Warehouse>();
        Warehouse zachodni = new Warehouse("Zachodni", 100);
        Warehouse górny = new Warehouse("Górny", 10);
        Warehouse duży = new Warehouse("Duży", 3);
        try {
            zachodni.storeInWarehouse(new ContBasic(334, 55));
        } catch (WarehouseStorageCapacityExceededException ee) {
            ee.printStackTrace();
        }
        warehouses.add(zachodni);
        return warehouses;
    }

    private static ArrayList<Ship> GenerateShips() {
        var ships = new ArrayList<Ship>();

        ShipCapacityInfo tinyCapacity = new ShipCapacityInfo(20,20000,6,6,6);
        ShipCapacityInfo smallCapacity = new ShipCapacityInfo(25,25000,5,6,6);
        ShipCapacityInfo mediumCapacity = new ShipCapacityInfo(50,50000,15,5, 7);
        ShipCapacityInfo bigCapacity = new ShipCapacityInfo(100,100000,25,10,15);
        ShipCapacityInfo hugeCapacity = new ShipCapacityInfo(1000,1000000,100,100,100);

        var rand = new Random();
        List<ContBasic> contBasics = new ArrayList<>();

        for(int i = 0; i < 8; i ++){
            contBasics.add(new ContBasic(rand.nextDouble(200), rand.nextInt(100000,200000000)));
        }
        for(int i = 0; i < 3; i ++){
            contBasics.add(new ContToxicLiquid(rand.nextDouble(200),  rand.nextInt(100000,200000000),  rand.nextInt(10000),  rand.nextDouble(5000), Pollutions.values()[rand.nextInt(Pollutions.values().length)], "Compound " + rand.nextInt(100) + (char)rand.nextInt(65, 90)));
        }
        for(int i = 0; i < 3; i ++){
            contBasics.add(new ContCooling(rand.nextDouble(200),  rand.nextInt(100000,200000000),  rand.nextInt(10000),  rand.nextDouble(5000)));
        }
        for(int i = 0; i < 3; i ++){
            contBasics.add(new ContExplosive(rand.nextDouble(200),  rand.nextInt(100000,200000000),  rand.nextInt(10000),  rand.nextDouble(500)));
        }
        for(int i = 0; i < 3; i ++){
            contBasics.add(new ContHeavy(rand.nextDouble(400),  rand.nextInt(100000,200000000),  rand.nextInt(10000)));
        }
        for(int i = 0; i < 3; i ++){
            contBasics.add(new ContLiquid(rand.nextDouble(200), rand.nextInt(100000,200000000), rand.nextDouble(5000)));
        }
        for(int i = 0; i < 3; i ++){
            contBasics.add(new ContToxicLoose(rand.nextDouble(200),  rand.nextInt(100000,200000000),  rand.nextInt(10000), Pollutions.values()[rand.nextInt(Pollutions.values().length)], rand.nextBoolean()));
        }

        var zenobia = new Ship ("Zenobia", "Port Newark", "Gdańsk","Maryport", tinyCapacity);
        ships.add(zenobia);
        var victoria = new Ship ("Victoria", "Port of Tacoma", "Eden Harbour","Port Botany", smallCapacity);
        ships.add(victoria);
        var titanic = new Ship ("Titanic", "Porthoustock", "Bideford","Birkenhead", mediumCapacity);
        ships.add(titanic);
        var kalmar = new Ship ("Kalmar", "Seville", "Huelva","Port of Long Beach", bigCapacity);
        ships.add(kalmar);
        var octopus = new Ship ("Ośmiornica", "Bilbao", "Harbour of Yamba","Llandulas", hugeCapacity);
        ships.add(octopus);

        for(Ship ship : ships){
            for(int i = 0; i<5; i++){
                var container = contBasics.remove(rand.nextInt(contBasics.size()-1));
                try{
                    ship.loadContainer(container);
                }
                catch (Exception e){

                }
            }
        }

        return ships;
    }
}
