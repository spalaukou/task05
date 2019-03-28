package by.epam.javawebtraining.spalaukou.logic;

import by.epam.javawebtraining.spalaukou.model.entity.Route;
import by.epam.javawebtraining.spalaukou.model.entity.Train;
import by.epam.javawebtraining.spalaukou.model.entity.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class TrainQueue {
    private List<Train> storage;
//    private Train train;
//    private boolean empty = true;

    public TrainQueue() {
        storage = new ArrayList<>();
    }

    public synchronized void add(Train train) {
        notifyAll();
        storage.add(train);
        System.out.println("Train arrives and is added to the queue: " + train);
    }

    public synchronized Train get(Route route, int tunnelNumber) {
        try {
            if(storage.size() > 0) {
                notifyAll();
                for (Train train : storage) {
                    if (train.getRoute() == route) {
                        storage.remove(train);
                        return train;
                    }
                }
                Train toReturn = storage.get(0);
                storage.remove(0);
                return toReturn;
            }
            System.out.println("Tunnel " + tunnelNumber + " checks: No trains in the queue");
            new TrainProducer(this, 4);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*public synchronized void add(Train train) {
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
        empty = true;
        notify();
        return train;
    }*/
}
