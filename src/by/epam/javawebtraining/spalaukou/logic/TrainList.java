package by.epam.javawebtraining.spalaukou.logic;

import by.epam.javawebtraining.spalaukou.model.entity.Route;
import by.epam.javawebtraining.spalaukou.model.entity.Train;
import by.epam.javawebtraining.spalaukou.model.exception.EmptyQueueException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class TrainList {
    private static final Logger LOGGER = Logger.getRootLogger();

    private List<Train> storage;

    public TrainList() {
        storage = new CopyOnWriteArrayList<>();
    }

    public void add(Train train) {
        storage.add(train);
        LOGGER.trace("Train arrives and is added to the queue: " + train);
    }

    public Train get(Route route, int tunnelNumber) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        if (storage.size() > 0) {
            for (Train train : storage) {
                if (train.getRoute() == route) {
                    storage.remove(train);
                    return train;
                } else {
                    Train toReturn = storage.get(0);
                    storage.remove(0);
                    return toReturn;
                }
            }
        }
        try {
            throw new EmptyQueueException();
        } catch (EmptyQueueException e) {
            LOGGER.error(e);
        }
        LOGGER.trace("Tunnel " + tunnelNumber + " checks: No trains in the queue.");
        return null;
    }
}
