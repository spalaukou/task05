package by.epam.javawebtraining.spalaukou.logic;

import by.epam.javawebtraining.spalaukou.model.entity.Route;
import by.epam.javawebtraining.spalaukou.model.entity.Train;
import by.epam.javawebtraining.spalaukou.model.entity.Type;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class TrainProducer implements Runnable {
    private static final Logger LOGGER = Logger.getRootLogger();

    private TrainList trainList;
    private Thread thread;
    private int trainCount;

    public TrainProducer(TrainList trainList, int trainCount) {
        this.trainList = trainList;
        this.trainCount = trainCount;
        thread = new Thread(this);
        thread.setName("-=Producer=-");
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
            trainList.add(randomTrain);
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(2));
            } catch (InterruptedException e) {
                LOGGER.error(e);
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
