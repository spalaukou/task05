package by.epam.javawebtraining.spalaukou.controller;

import java.util.Random;

/**
 * @author Stanislau Palaukou on 26.03.2019
 * @project task05
 */

public class FirstThread extends Thread {

    public FirstThread() {
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Child thread " + getName());
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {

            }
        }
    }
}
