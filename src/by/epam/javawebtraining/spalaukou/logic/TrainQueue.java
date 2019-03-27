package by.epam.javawebtraining.spalaukou.logic;

import by.epam.javawebtraining.spalaukou.model.entity.Train;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class TrainQueue {
    private Train train;
    private boolean empty = true;

    public TrainQueue() {
    }

    public synchronized void add(Train train) {
        if (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Log interruptedException");
            }
        }
        System.out.println("Added to the queue: " + train);
        this.train = train;
        empty = false;
        notify();
    }

    public synchronized Train get() {
        if (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Log interruptedException");
            }
        }
        System.out.println("Drives in tunnel: " + train);
        empty = true;
        notify();
        return train;
    }
}
