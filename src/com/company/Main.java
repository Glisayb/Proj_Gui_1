package com.company;

import Models.*;
import Models.Containers.ContHeavy;
import Models.Containers.ContToxicLiquid;
import Models.Containers.IContHeavy;

public class Main {

    public static void main(String[] args) {

        ShipCapacityInfo intel1 = new ShipCapacityInfo(44,21.37,10,7,13);
        ShipCurentLoad load1= new ShipCurentLoad();
        Ship latajacy = new Ship("latajacyholender","Amsterdam","Borholm","Danzig", intel1, load1);
        Warehouse WZ =new Warehouse("wschodniozachodni", 666);
        IContHeavy grubas = new ContHeavy(22,44,200);
        ContToxicLiquid bimber = new ContToxicLiquid( 121,33,90900,92.7, Pollutions.Alkoholic,"Skittlesowka");
        System.out.println(bimber.getDensity());

    }
}
