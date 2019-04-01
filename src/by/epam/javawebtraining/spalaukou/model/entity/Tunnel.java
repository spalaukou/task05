package by.epam.javawebtraining.spalaukou.model.entity;

import by.epam.javawebtraining.spalaukou.logic.TrainQueue;
import by.epam.javawebtraining.spalaukou.model.exception.MaxInOneDirectionException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class Tunnel implements Runnable {
    private static final Logger logger = Logger.getRootLogger();
    private static final int MAX_IN_ONE_DIRECTION = 3;
    private static final int TUNNEL_CAPACITY = 2;

    private Lock lock;

    private static int tunnelCount;
    private TrainQueue trainQueue;
    private Thread thread;
    private Route route;
    private int routeCount;
    private int tunnelNumber;
    private List<Train> trains;

    public Tunnel(TrainQueue trainQueue) {
        lock = new ReentrantLock();
        this.trainQueue = trainQueue;
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
            logger.trace(thread.getName() + " checks: " + trainQueue.getLock());
//            System.out.println(thread.getName() + " checks: " + trainQueue.getLock());

            if (lock.tryLock()) {
                lock.lock();
                try {
                    Train train = trainQueue.get(route, tunnelNumber);
                    if (train != null) {
                        if (train.getRoute() == route) {
                            if (routeCount++ == MAX_IN_ONE_DIRECTION) {
                                try {
                                    throw new MaxInOneDirectionException();
                                } catch (MaxInOneDirectionException e) {
                                    //log
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
                        logger.trace(train + " drives in the tunnel " + tunnelNumber);
                        logger.trace("Priority route in the tunnel " + tunnelNumber + " is " + route +
                                ". Trains already passed: " + routeCount);
//                        System.out.println(train + " drives in the tunnel " + tunnelNumber);
//                        System.out.println("Priority route in the tunnel " + tunnelNumber + " is " + route +
//                                ". Trains already passed: " + routeCount);
//                        System.out.println(tunnelNumber + " " + trains);
//                        System.out.println("---------------------------------------------------");
                    } else {
                        flag = false;
                    }
                    try {
                        Thread.sleep(1000);
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
