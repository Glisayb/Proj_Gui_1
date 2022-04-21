package com.company;

import Models.Ship;

public class ShowAllShipsCommand implements ICommand{
    
    public ShowAllShipsCommand(){
        
    }
    
    @Override
    public void execute() {
        System.out.println("ID statków w danym porcie");
        for (int i = 0;i<Main.ships.size(); i++) {
            System.out.println(i+1 + " " + Main.ships.get(i).shipId);
        }
    }
}
