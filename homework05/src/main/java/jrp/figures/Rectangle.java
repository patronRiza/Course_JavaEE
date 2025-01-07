package jrp.figures;

public class Rectangle extends Figure {
    protected final int sizeX;
    protected final int sizeY;

    public Rectangle(String name, int positionX, int positionY, int sizeX, int sizeY) {
        super(name, positionX, positionY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public Double getPerimeter() {
        return (double) (sizeX * 4 + sizeY * 4);
    }

    @Override
    public String toString() {
        return "Rectangle {" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                "}\n";
    }
}
