package by.epam.javawebtraining.spalaukou.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stanislau Palaukou on 31.03.2019
 * @project task05
 */

public class NewMain {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Tunnel tunnel1 = new Tunnel(resource);
        Tunnel tunnel2 = new Tunnel(resource);
    }
}

class Tunnel implements Runnable {
    static int number;
    Resource resource;
    Thread thread;

    public Tunnel(Resource resource) {
        this.resource = resource;
        thread = new Thread(this);
        thread.setName("Tunnel " + ++number);
        thread.start();
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            System.out.println(thread.getName() + " checks: " + resource.getLock());

            String train = resource.getTrain();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (train != null) {
                System.out.println(thread.getName() + " - " + train);
            } else {
                flag = false;
            }
        }
    }
}

class Resource {
    List<String> trains = new ArrayList<>();
    Lock lock = new ReentrantLock();

    public Resource() {
        trains.add("Train 1");
        trains.add("Train 2");
        trains.add("Train 3");
        trains.add("Train 4");
        trains.add("Train 5");
    }

    public Lock getLock() {
        return lock;
    }

    public String getTrain() {
        lock.lock();
        try {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                //log
            }
            if (trains.size() > 0) {
                String temp = trains.get(0);
                trains.remove(0);

                return temp;
            }
            System.out.println("Tunnel checks trains...");
            return null;
        } finally {
            lock.unlock();
        }
    }
}
