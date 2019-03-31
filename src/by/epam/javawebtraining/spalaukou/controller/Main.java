package by.epam.javawebtraining.spalaukou.controller;

import by.epam.javawebtraining.spalaukou.logic.TrainProducer;
import by.epam.javawebtraining.spalaukou.logic.TrainQueue;
import by.epam.javawebtraining.spalaukou.model.entity.Route;
import by.epam.javawebtraining.spalaukou.model.entity.Train;
import by.epam.javawebtraining.spalaukou.model.entity.Tunnel;
import by.epam.javawebtraining.spalaukou.model.entity.Type;

import java.util.Random;

/**
 * @author Stanislau Palaukou on 26.03.2019
 * @project task05
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());

        TrainQueue trainQueue = new TrainQueue();
        TrainProducer trainProducer = new TrainProducer(trainQueue, 10);
        Tunnel tunnel1 = new Tunnel(trainQueue);
        Tunnel tunnel2 = new Tunnel(trainQueue);

        trainProducer.getThread().join();
        tunnel1.getThread().join();
        tunnel2.getThread().join();

        System.out.println("End main");

    }
}
