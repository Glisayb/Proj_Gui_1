package com.company;

public class TrainRunning implements Runnable {

private static int n;

    public TrainRunning() {
        this.n = 1;
    }

    @Override
    public void run() {
        while (true) {
            int x = 8;
            System.out.printf("Pociąg załadowany, odliczanie d% sekund do podstawienia nowego składu \n", x);
            for (int i = 0; i < 10; i++) {
                Main.containersIntoTrain.remove(i);
            }
            try {
                for (int i = 0; i < x; i++) {
                    Thread.sleep(1000);
                    System.out.printf("%d sekund \n", i);
                }
            } catch
            (InterruptedException e) {
            }
            System.out.println("Nowy skład podstawiony");
            if (Main.containersIntoTrain.size()>=9) {
                n++;
                System.out.printf("%d pętla rozładunku \n", n);
                run();
            }
            else Thread.interrupted();
        }
    }
}

