package jrp.utils;

import jrp.figures.Figure;

import java.util.concurrent.ThreadLocalRandom;

public abstract class RandomChangePosition {

    private RandomChangePosition() {
    }

    public static void changePosition(Figure figure) {

        figure.setPositionX(ThreadLocalRandom.current().nextInt(-100, 100));
        figure.setPositionY(ThreadLocalRandom.current().nextInt(-100, 100));

    }
}
