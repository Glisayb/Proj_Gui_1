package com.company;

public class Train<shipsIntoTrain> implements Runnable {

    @Override
    public void run() {
        if (Main.shipsIntoTrain.size() >= 10) {
            System.out.println("Pociąg załadowany, odliczanie do podstawienia nowego składu");
            for (int i = 0; i<10;i++) {
                Main.shipsIntoTrain.remove(i);
            }
            try {
                for (int i =0;i<8;i++) {
                    Thread.sleep(1000);
                    System.out.printf("%d sekund \n", i);
                }
            } catch
            (InterruptedException e) {}
            System.out.println("Nowy skład podstawiony");
            if (Main.shipsIntoTrain.size() >= 10){
                run();
            }
        }
    }
}
