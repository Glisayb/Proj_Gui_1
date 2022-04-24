package com.company;

import Commands.ICommand;

import java.util.Scanner;

public class Menu implements Runnable {


    CommandFactory commandFactory = new CommandFactory();
    @Override
    public void run() {
        System.out.println("Menu applikacji portowej");
        System.out.println("Dostępne komendy:");
        for (String s : commandFactory.GetAvailableCommands())
            System.out.println("\t- " + s);
        System.out.println();
        Scanner input = new Scanner(System.in);
        while (true) {
            if (input.hasNextLine()) {
                var commandInput = input.nextLine();
                ICommand command;
                try {
                    command = commandFactory.GetCommand(commandInput);
                    command.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
