package by.epam.javawebtraining.spalaukou.logic;

import by.epam.javawebtraining.spalaukou.model.entity.Route;
import by.epam.javawebtraining.spalaukou.model.entity.Train;
import by.epam.javawebtraining.spalaukou.model.exception.EmptyQueueException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class TrainQueue {
    private static final Logger logger = Logger.getRootLogger();

    private List<Train> storage;
    private Lock lock;
    private Lock lockAdd;

    public TrainQueue() {
        storage = new CopyOnWriteArrayList<>();
        lock = new ReentrantLock();
        lockAdd = new ReentrantLock();
    }

    public Lock getLock() {
        return lock;
    }

    //    public synchronized void add(Train train) {
    public void add(Train train) {
//        notifyAll();
        lockAdd.lock();
        try {
            storage.add(train);
            logger.trace("Train arrives and is added to the queue: " + train);
//            System.out.println("Train arrives and is added to the queue: " + train);
        } finally {
            lockAdd.unlock();
        }
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
            logger.trace("Tunnel " + tunnelNumber + " checks: No trains in the queue.");
//            System.out.println("Tunnel " + tunnelNumber + " checks: No trains in the queue.");
            return null;
        } finally {
            lock.unlock();
        }
    }
}
