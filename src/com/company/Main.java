package com.company;

import Models.Containers.ContHeavy;
import Models.Containers.ContToxicLiquid;
import Models.Containers.IContHeavy;
import Models.Ship;
import Models.Warehouse;

public class Main {

    public static void main(String[] args) {

        Ship latajacy = new Ship("latajacyholender","Amsterdam","Borholm","Danzig",44,21.37,10,7,13);
        Warehouse WZ =new Warehouse("wschodniozachodni", 666);
        IContHeavy grubas = new ContHeavy(22,44,200);
        ContToxicLiquid bimber = new ContToxicLiquid( 121,33,90900,92.7,"alkohol");
        System.out.println(bimber.getDensity());

    }
}
