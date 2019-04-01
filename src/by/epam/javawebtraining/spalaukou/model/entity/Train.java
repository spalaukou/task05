package by.epam.javawebtraining.spalaukou.model.entity;

/**
 * @author Stanislau Palaukou on 27.03.2019
 * @project task05
 */

public class Train {
    private static int trainCount = 0;

    private Type type;
    private Route route;
    private int number;

    public Train(Type type, Route route) {
        this.type = type;
        this.route = route;
        trainCount++;
        number = trainCount;
    }

    public Type getType() {
        return type;
    }

    public Route getRoute() {
        return route;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Train " + number + " " +
                type + " " + route;
    }
}
