package com.company;

import Models.Ship;
import Models.ShipCapacityInfo;

public class CreateShipCommand implements ICommand {

    public String name;
    public String homeport;
    public String from;
    public String destination;
    public ShipCapacityInfo shipCapacityMax;

    public CreateShipCommand(String name, String homeport, String from, String destination,
                             int capacity, double weight, int heavy, int electrified, int hazardous) {
        this.name = name;
        this.homeport = homeport;
        this.from = from;
        this.destination = destination;
        this.shipCapacityMax = new ShipCapacityInfo(capacity,  weight, heavy, electrified, hazardous);
    }


    @Override
    public void execute() {

    Ship ship = new Ship(name,homeport,from,destination,shipCapacityMax);
    Main.ships.add(ship);

    }
}


