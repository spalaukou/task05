package by.epam.javawebtraining.spalaukou.logic;

import by.epam.javawebtraining.spalaukou.model.entity.Route;
import by.epam.javawebtraining.spalaukou.model.entity.Train;
import by.epam.javawebtraining.spalaukou.model.entity.Type;
import by.epam.javawebtraining.spalaukou.model.exception.EmptyQueueException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class TrainQueue {
    private List<Train> storage;
    private Lock lock = new ReentrantLock();
    private Lock lockAdd = new ReentrantLock();

    public TrainQueue() {
        //storage = new ArrayList<>();
        storage = new CopyOnWriteArrayList<>();
    }

    public Lock getLock() {
        return lock;
    }

//    public synchronized void add(Train train) {
    public void add(Train train) {
//        notifyAll();
        lockAdd.lock();
        storage.add(train);
        System.out.println("Train arrives and is added to the queue: " + train);
        lockAdd.unlock();
    }

//    public synchronized Train get(Route route, int tunnelNumber) {
    public Train get(Route route, int tunnelNumber) {
        lock.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //log
            }
            if (storage.size() > 0) {
//            notifyAll();
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
            try {
                throw new EmptyQueueException();
            } catch (EmptyQueueException e) {
                //log
            }
            System.out.println("Tunnel " + tunnelNumber + " checks: No trains in the queue.");
            return null;
        } finally {
            lock.unlock();
        }
    }
}
