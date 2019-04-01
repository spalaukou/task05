package by.epam.javawebtraining.spalaukou.controller;

import by.epam.javawebtraining.spalaukou.logic.TrainProducer;
import by.epam.javawebtraining.spalaukou.logic.TrainQueue;
import by.epam.javawebtraining.spalaukou.model.entity.Tunnel;
import org.apache.log4j.Logger;

/**
 * @author Stanislau Palaukou on 26.03.2019
 * @project task05
 */

public class Main {
    private static final Logger logger = Logger.getRootLogger();

    public static void main(String[] args) throws InterruptedException {
        logger.trace("Available number of cores: " + Runtime.getRuntime().availableProcessors());
//        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());

        TrainQueue trainQueue = new TrainQueue();
        TrainProducer trainProducer = new TrainProducer(trainQueue, 10);
        Tunnel tunnel1 = new Tunnel(trainQueue);
        Tunnel tunnel2 = new Tunnel(trainQueue);

        trainProducer.getThread().join();
        tunnel1.getThread().join();
        tunnel2.getThread().join();

        System.out.println(tunnel1.getTrains());
        System.out.println(tunnel2.getTrains());

        logger.trace("End main thread.");
//        System.out.println("End main thread.");

    }
}
