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

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        int count = 0;

        while (count < trainCount) {
            Train randomTrain = new Train(getRandomType(), getRandomRoute());
            count++;
            trainQueue.add(randomTrain);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
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
