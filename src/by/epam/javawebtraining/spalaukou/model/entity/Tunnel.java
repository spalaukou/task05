package by.epam.javawebtraining.spalaukou.model.entity;

import by.epam.javawebtraining.spalaukou.logic.TrainQueue;

import java.util.Random;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class Tunnel implements Runnable {
    private static int tunnelCount;
    private TrainQueue trainQueue;
    private Thread thread;
    private Route route;
    private int routeCount;
    private int tunnelNumber;

    public Tunnel(TrainQueue trainQueue) {
        this.trainQueue = trainQueue;
        this.route = getRandomRoute();
        tunnelCount++;
        tunnelNumber = tunnelCount;
        thread = new Thread(this);
        thread.setName("-=Tunnel " + tunnelNumber + "=-");
        thread.start();
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        int maxInOneDirection = 3;

        boolean flag = true;
        while (flag) {
//            if(trainQueue.getLock().tryLock()) {
            Train train = trainQueue.get(route, tunnelNumber);
            if (train != null) {
                if (train.getRoute() == route) {
                    if (routeCount++ == maxInOneDirection) {
                        changeRoute();
                        routeCount = 0;
                    }
                } else {
                    changeRoute();
                    routeCount = 1;
                }
                System.out.println(train + " drives in the tunnel " + tunnelNumber);
                System.out.println("Priority route in the tunnel " + tunnelNumber + " is " + route +
                        ". Trains already passed: " + routeCount);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            }
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
