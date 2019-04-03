package by.epam.javawebtraining.spalaukou.controller;

import by.epam.javawebtraining.spalaukou.logic.TrainProducer;
import by.epam.javawebtraining.spalaukou.logic.TrainList;
import by.epam.javawebtraining.spalaukou.model.entity.Tunnel;
import org.apache.log4j.Logger;

/**
 * @author Stanislau Palaukou on 26.03.2019
 * @project task05
 */

public class Main {
    private static final Logger LOGGER = Logger.getRootLogger();
    private static final int TRAINS_TO_PRODUCE = 10;

    public static void main(String[] args) throws InterruptedException {
        LOGGER.trace("Available number of cores: " + Runtime.getRuntime().availableProcessors());

        TrainList trainList = new TrainList();
        TrainProducer trainProducer = new TrainProducer(trainList, TRAINS_TO_PRODUCE);
        Tunnel tunnel1 = new Tunnel(trainList);
        Tunnel tunnel2 = new Tunnel(trainList);

        trainProducer.getThread().join();
        tunnel1.getThread().join();
        tunnel2.getThread().join();

        LOGGER.trace("End main thread.");
    }
}
