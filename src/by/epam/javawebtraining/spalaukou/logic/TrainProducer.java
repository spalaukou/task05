package by.epam.javawebtraining.spalaukou.logic;

import by.epam.javawebtraining.spalaukou.model.entity.Route;
import by.epam.javawebtraining.spalaukou.model.entity.Train;
import by.epam.javawebtraining.spalaukou.model.entity.Type;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class TrainProducer implements Runnable {
    private TrainQueue trainQueue;
    private Thread thread;
    private int trainCount;

    public TrainProducer(TrainQueue trainQueue, int trainCount) {
        this.trainQueue = trainQueue;
        this.trainCount = trainCount;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        int count = 0;

        while (count < trainCount) {
            Train randomTrain = new Train(getRandomType(), getRandomRoute());
            System.out.println("Arrives: " + randomTrain);
            count++;
            trainQueue.add(randomTrain);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Log interruptedException");
            }

        }
    }

    private Type getRandomType() {
        return Type.values()[new Random().nextInt(Type.values().length)];
    }

    private Route getRandomRoute() {
        return Route.values()[new Random().nextInt(Route.values().length)];
    }
}
