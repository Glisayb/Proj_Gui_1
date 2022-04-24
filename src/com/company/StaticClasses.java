package com.company;
import Models.Containers.ContBasic;
import Models.Ship;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class StaticClasses {

    public static class IdGenerator {

        public static String Generate() {
            UUID uuid = UUID.randomUUID();
            return (uuid.toString());
        }
    }

    public static class Timer extends Thread{
        public static LocalDate date = LocalDate.now();

    }
    public static Ship getShip(String id){
        return Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst().get();
    }
    public static void farewell(String shipId){
        LocalTime time = LocalTime.now();
        Ship ship = StaticClasses.getShip(shipId);
        String name = ship.name;
        System.out.printf("%s ETD: %s \n\t Farwell miss  %s",
                name,  time, name);
        Main.ships.remove(StaticClasses.getShip(shipId));
    }

    public void loadTrain(Ship ship){

        var list = ship.containerList;
        for (ContBasic container : list){
            Main.shipsIntoTrain.add(container);
            ship.containerList.remove(container);
        }
        Train.run();
    }

}