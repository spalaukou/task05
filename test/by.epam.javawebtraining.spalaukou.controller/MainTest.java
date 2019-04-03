package by.epam.javawebtraining.spalaukou.controller;

import by.epam.javawebtraining.spalaukou.logic.TrainList;
import by.epam.javawebtraining.spalaukou.model.entity.Route;
import by.epam.javawebtraining.spalaukou.model.entity.Train;
import by.epam.javawebtraining.spalaukou.model.entity.Tunnel;
import by.epam.javawebtraining.spalaukou.model.entity.Type;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Stanislau Palaukou on 01.04.2019
 * @project task05
 */

public class MainTest {
    TrainList trainList;
    Tunnel tunnel1;
    Tunnel tunnel2;
    List<Train> expected1;
    List<Train> expected2;
    Train train1;
    Train train2;
    Train train3;
    Train train4;

    @BeforeMethod
    public void setUp() {
        train1 = new Train(Type.CARGO, Route.SALOU_BARCELONA);
        train2 = new Train(Type.CARGO, Route.BARCELONA_SALOU);
        train3 = new Train(Type.PASSENGER, Route.SALOU_BARCELONA);
        train4 = new Train(Type.SPEEDY, Route.SALOU_BARCELONA);

        trainList = new TrainList();
        trainList.add(train1);
        trainList.add(train2);
        trainList.add(train3);
        trainList.add(train4);
    }

    @Test
    public void testMain() throws InterruptedException {
        tunnel1 = new Tunnel(trainList);
        tunnel2 = new Tunnel(trainList);

        tunnel1.getThread().join();
        tunnel2.getThread().join();

        expected1 = new ArrayList<>();
        expected1.add(train3);

        expected2 = new ArrayList<>();
        expected2.add(train1);
        expected2.add(train4);

        //assertEquals(tunnel1.getTrains(), expected1);
        assertEquals(tunnel2.getTrains(), expected2);
    }
}