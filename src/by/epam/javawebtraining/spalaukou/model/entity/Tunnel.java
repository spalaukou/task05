package by.epam.javawebtraining.spalaukou.model.entity;

import by.epam.javawebtraining.spalaukou.logic.TrainQueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class Tunnel implements Runnable {
    private static int tunnelCount = 0;
    private TrainQueue trainQueue;
    private Thread thread;
    private Route route;
    private int routeCount;
    private int tunnelNumber;

    public Tunnel(TrainQueue trainQueue) {
        this.trainQueue = trainQueue;
        this.route = Route.SALOU_BARCELONA;
        tunnelCount++;
        tunnelNumber = tunnelCount;
        thread = new Thread(this);
        thread.start();
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        int maxOneDirection = 2;

        while (true) {
            try {
                Thread.sleep(1000);
                Train train = trainQueue.get(route, tunnelNumber);
                if (train != null) {
                    if(train.getRoute() == route) {
                        if (routeCount++ > maxOneDirection) {
                            changeRoute();
                            routeCount = 0;
                        }
                        System.out.println(train + " drives in the tunnel " + tunnelNumber);
                        System.out.println("Priority route in the tunnel " + tunnelNumber + " is " + route + ". Trains already passed " + routeCount);
                    } else {
                        changeRoute();
                        routeCount = 0;
                        System.out.println(train + " drives in the tunnel " + tunnelNumber);
                        System.out.println("Priority route in the tunnel " + tunnelNumber + " is " + route + ". Trains already passed " + routeCount);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeRoute() {
        Route newRoute = Route.values()[new Random().nextInt(Route.values().length)];
        if (route == newRoute) {
            changeRoute();
        } else {
            route = newRoute;
        }
    }
}
