package jrp.figures;

import jrp.interfaces.Moveable;
import jrp.utils.RandomChangePosition;

public class Square extends Rectangle implements Moveable {

    public Square(String name, int positionX, int positionY, int sizeX, int sizeY) {
        super(name, positionX, positionY, sizeX, sizeY);
    }

    @Override
    public void moveRandom() {
        RandomChangePosition.changePosition(this);
    }

    @Override
    public void moveFigure(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    @Override
    public String toString() {
        return "Square {" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                "}\n";
    }
}
