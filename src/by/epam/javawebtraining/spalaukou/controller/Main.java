package by.epam.javawebtraining.spalaukou.controller;

import java.util.Random;

/**
 * @author Stanislau Palaukou on 26.03.2019
 * @project task05
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        FirstThread firstThread = new FirstThread();
//        firstThread.start();
        FirstThread firstThread = new FirstThread();

//        SecondThread secondThread = new SecondThread();
//        Thread thread = new Thread(secondThread);
//        thread.start();
        SecondThread secondThread = new SecondThread();

        firstThread.join();
        secondThread.getThread().join();

        for (int i = 0; i < 10; i++) {
            System.out.println("Main thread");
            Thread.sleep(new Random().nextInt(1000));
        }
    }
}
