package com.company;

import Models.Containers.ContHeavy;
import Models.Containers.IContHeavy;
import Models.Ship;

public class Main {

    public static void main(String[] args) {

        Ship latajacy = new Ship("latajacyholender","Amsterdam","Borholm","Danzig",44,21.37,10,7,13);
        IContHeavy grubas = new ContHeavy(22,44,200);

    }
}
