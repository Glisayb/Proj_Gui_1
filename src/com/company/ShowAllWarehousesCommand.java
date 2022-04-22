package com.company;

import Models.Ship;

public class ShowAllWarehousesCommand implements ICommand{

    public ShowAllWarehousesCommand(){

    }

    @Override
    public void execute() {
        System.out.println("Nazwy i zajętość magazynów w danym porcie :");
        for (int i = 0;i<Main.warehouses.size(); i++) {
            System.out.println(i+1 + " " + Main.warehouses.get(i).name);
            System.out.println(Main.warehouses.get(i).warCollection.size()+" z "+Main.warehouses.get(i).capacity);
        }
    }
}