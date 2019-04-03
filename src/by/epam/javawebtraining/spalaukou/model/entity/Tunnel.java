package by.epam.javawebtraining.spalaukou.model.entity;

import by.epam.javawebtraining.spalaukou.logic.TrainList;
import by.epam.javawebtraining.spalaukou.model.exception.MaxInOneDirectionException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class Tunnel implements Runnable {
    private static final Logger LOGGER = Logger.getRootLogger();
    private static final int MAX_IN_ONE_DIRECTION = 3;
    private static final int TUNNEL_CAPACITY = 2;

    private Lock lock;

    private static int tunnelCount;
    private TrainList trainList;
    private Thread thread;
    private Route route;
    private int routeCount;
    private int tunnelNumber;
    private List<Train> trains;

    public Tunnel(TrainList trainList) {
        lock = new ReentrantLock();
        this.trainList = trainList;
        this.route = getRandomRoute();
        trains = new CopyOnWriteArrayList<>();
        tunnelCount++;
        tunnelNumber = tunnelCount;
        thread = new Thread(this);
        thread.setName("-=Tunnel " + tunnelNumber + "=-");
        thread.start();
    }

    public List<Train> getTrains() {
        return trains;
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {

            if (lock.tryLock()) {
                lock.lock();
                try {
                    Train train = trainList.get(route, tunnelNumber);
                    if (train != null) {
                        if (train.getRoute() == route) {
                            if (routeCount++ == MAX_IN_ONE_DIRECTION) {
                                try {
                                    throw new MaxInOneDirectionException();
                                } catch (MaxInOneDirectionException e) {
                                    LOGGER.error(e);
                                }
                                changeRoute();
                                routeCount = 1;
                            }
                        } else {
                            changeRoute();
                            routeCount = 1;
                        }

                        if (trains.size() != 0) {
                            if (train.getRoute() != trains.get(0).getRoute()) {
                                for (Train t : trains) {
                                    trains.remove(0);
                                }
                                trains.add(train);
                            } else {
                                if (trains.size() == TUNNEL_CAPACITY) {
                                    trains.remove(0);
                                    trains.add(train);
                                } else {
                                    trains.add(train);
                                }
                            }
                        } else {
                            trains.add(train);
                        }
                        LOGGER.trace(train + " drives in the tunnel " + tunnelNumber);
                        LOGGER.trace("Priority route in the tunnel " + tunnelNumber + " is " + route +
                                ". Trains already passed: " + routeCount);
                        LOGGER.trace("Right now in the tunnel " + tunnelNumber + ": " + trains);
                    } else {
                        flag = false;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private void changeRoute() {
        Route newRoute = getRandomRoute();
        if (route == newRoute) {
            changeRoute();
        } else {
            route = newRoute;
        }
    }

    private Route getRandomRoute() {
        return Route.values()[new Random().nextInt(Route.values().length)];
    }
}
