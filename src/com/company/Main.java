package com.company;

import Exceptions.WarehouseItemNotFoundException;
import Exceptions.WarehouseStorageCapacityExceededException;
import Models.*;
import Models.Containers.*;
import Persistance.PersistanceStatics;
import Persistance.ShipPersistance;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ShipCapacityInfo intel1 = new ShipCapacityInfo(4224,2137,10,7,13);
        ShipCapacityInfo intel2 = new ShipCapacityInfo(1245,1236,3,1,7);
        ShipCapacityInfo intel3 = new ShipCapacityInfo(222,3769,5,2,5);

        Ship zenobia = new Ship ("Zenobia", "Nowy York", "Lodz","London", intel2);
        Ship amanda = new Ship("Amanda","Amsterdam","Borholm","Danzig", intel1);
        Ship suzie = new Ship("Suzie","Radom","Ulaanbaatar","Montevideo", intel3);

        ContBasic basic = new ContBasic(321.34,12);
        ContHeavy heavy = new ContHeavy(222,44,200);
        ContCooling cooling = new ContCooling(222,44,200, 33.4);
        ContExplosive explosive = new ContExplosive(421.11,87,90090,150);
        ContToxicLiquid toxicLiquid = new ContToxicLiquid( 121,33,90900,92.7, Pollutions.ALKOHOLIC,"Skittlesowka");
        ContToxicLoose toxicLoose = new ContToxicLoose(143.21,42,5555,Pollutions.STUPIDITY, true);
        ContExplosive explosive2 = new ContExplosive(33.21,17,10090,3000);
        ContToxicLiquid toxicLiquid2 = new ContToxicLiquid( 22,73,00700,4.0, Pollutions.NIGHT_POLLUTIONS,"Gęste");

        try{
            Boolean read = false;
            Boolean write = true;
            var path = "ship.txt";
            if(write){
                zenobia.loadContainer(basic);
                zenobia.loadContainer(heavy);
                zenobia.loadContainer(toxicLiquid);

                amanda.loadContainer(toxicLiquid);
                amanda.loadContainer(cooling);
                amanda.loadContainer(explosive);

                suzie.loadContainer(toxicLiquid);
                suzie.loadContainer(cooling);
                suzie.loadContainer(explosive);
                suzie.loadContainer(toxicLiquid2);
                suzie.loadContainer(explosive2);

                ArrayList<Ship> ships = new ArrayList<>();
                ships.add(zenobia);
                ships.add(amanda);
                ships.add(suzie);
                var shipsAsString = ShipPersistance.Store.PrepareListOfShips(ships);
                PersistanceStatics.FilePersistance.WriteFile(path, shipsAsString);
            }
            if(read){
                var shipsAsString = PersistanceStatics.FilePersistance.Read(path);
                var shipsLoaded = ShipPersistance.Store.CreateListOfShipsFromString(shipsAsString);
                System.out.println(shipsLoaded);
            }





        }catch(Exception e){
            System.out.println(e.toString());
        }

    }
}
