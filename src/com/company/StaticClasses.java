package com.company;
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
        System.out.println(name + " ETD :" + time +
                "\n Farwell miss "+ name );
        String.format
                ("%s ETD: %s \n Farwell miss  %s",
                        name,  time, name);
        Main.ships.remove(StaticClasses.getShip(shipId));
    }

}