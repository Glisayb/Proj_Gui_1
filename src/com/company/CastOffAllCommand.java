package com.company;

import Commands.ICommand;


public class CastOffAllCommand implements ICommand {

    public CastOffAllCommand(){

    }

    @Override
    public void execute() {
        while(Main.ships.size()>0){
            StaticClasses.farewell(Main.ships.get(Main.ships.size()-1).shipId);
        }
    }
}