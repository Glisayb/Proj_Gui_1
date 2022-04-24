package com.company;

import Commands.ICommand;

public class CastOffCommand implements ICommand {

    public String id;

    public CastOffCommand(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        StaticClasses.farewell(id);
    }
}


