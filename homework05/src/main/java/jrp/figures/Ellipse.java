package jrp.figures;

public class Ellipse extends Figure {

    protected final int sizeX;
    protected final int sizeY;

    public Ellipse(String name, int positionX, int positionY, int sizeX, int sizeY) {
        super(name,positionX, positionY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public Double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((double) (sizeX * sizeX + sizeY * sizeY) / 2);
    }

    @Override
    public String toString() {
        return "Ellipse {" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                "}\n";
    }
}
