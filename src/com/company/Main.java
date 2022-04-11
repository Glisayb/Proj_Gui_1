package com.company;

import Models.*;
import Models.Containers.ContBasic;
import Models.Containers.ContHeavy;
import Models.Containers.ContToxicLiquid;
import Models.Containers.IContHeavy;

public class Main {

    public static void main(String[] args) {

        ShipCapacityInfo maxintel1 = new ShipCapacityInfo(44,21.37,10,7,13);
        ShipCapacityInfo curentintel1 = new ShipCapacityInfo(11,1.3,1,2,3);
        Ship latajacy = new Ship("latajacyholender","Amsterdam","Borholm","Danzig", maxintel1);
        Warehouse WZ =new Warehouse("wschodniozachodni", 666);
        IContHeavy grubas = new ContHeavy(22,44,200);
        ContToxicLiquid bimber = new ContToxicLiquid( 121,33,90900,92.7, Pollutions.Alkoholic,"Skittlesowka");
        System.out.println(bimber.getDensity());

        ContBasic bazowy = new ContBasic(10,11);

        latajacy.loadContainer(bazowy);
        latajacy.loadContainer(bazowy);
        latajacy.loadContainer(bazowy);

    }
}
