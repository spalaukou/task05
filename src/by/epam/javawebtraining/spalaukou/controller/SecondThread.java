package by.epam.javawebtraining.spalaukou.controller;

import java.util.Random;

/**
 * @author Stanislau Palaukou on 26.03.2019
 * @project task05
 */

public class SecondThread implements Runnable {
    private Thread thread;

    public SecondThread() {
        thread = new Thread(this);
        thread.start();
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Child thread");
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {

            }
        }
    }
}
