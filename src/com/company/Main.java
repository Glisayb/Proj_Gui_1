package com.company;

import Models.Containers.ContBasic;
import Models.Containers.ContHeavy;
import Models.Containers.ContToxicLiquid;
import Models.Containers.IContHeavy;
import Models.Pollutions;
import Models.Ship;
import Models.ShipCapacityInfo;
import Models.Warehouse;

public class Main {

    public static void main(String[] args) {

        ShipCapacityInfo intel1 = new ShipCapacityInfo(44,21.37,10,7,13);
        Ship latajacy = new Ship("latajacyholender","Amsterdam","Borholm","Danzig", intel1);
        Warehouse WZ =new Warehouse("wschodniozachodni", 3);
        ContHeavy grubas = new ContHeavy(22,44,200);
        ContToxicLiquid bimber = new ContToxicLiquid( 121,33,90900,92.7, Pollutions.ALKOHOLIC,"Skittlesowka");
        System.out.println(bimber.getDensity());

        WZ.storeInWarehouse(grubas);
        WZ.storeInWarehouse(bimber);
        WZ.storeInWarehouse(new ContBasic(142,223));
        WZ.storeInWarehouse(new ContBasic(132,11));

    }
}
