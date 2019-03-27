package by.epam.javawebtraining.spalaukou.model.entity;

import by.epam.javawebtraining.spalaukou.logic.TrainQueue;

import java.util.concurrent.TimeUnit;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class Tunnel implements Runnable {
    private static int tunnelCount = 0;
    private TrainQueue trainQueue;
    private Thread thread;
    private int tunnelNumber;


    public Tunnel(TrainQueue trainQueue) {
        this.trainQueue = trainQueue;
        tunnelCount++;
        tunnelNumber = tunnelCount;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                trainQueue.get();
            } catch (InterruptedException e) {
                System.out.println("Log interruptedException");
            }
        }
    }
}
