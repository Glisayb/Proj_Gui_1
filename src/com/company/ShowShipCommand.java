package com.company;

import Models.Ship;
import Persistance.ShipPersistance;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class ShowShipCommand implements ICommand{

    private String id;

    public ShowShipCommand(String id){

        this.id = id;
    }

    @Override
    public void execute() {

        var ship = Main.ships.stream().filter(s -> Objects.equals(s.shipId, id)).findFirst();
        if(ship.isPresent()){
            var list = new ArrayList<Ship>();
            list.add(ship.get());
            System.out.println(ShipPersistance.Store.PrepareListOfShips(list));
        }
    }
}
