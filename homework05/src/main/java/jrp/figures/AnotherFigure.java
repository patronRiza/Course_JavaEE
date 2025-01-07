package jrp.figures;

public class AnotherFigure extends Figure {

    private final int sizeX;
    private final int sizeY;

    public AnotherFigure(String name, int positionX, int positionY, int sizeX, int sizeY) {
        super(name, positionX, positionY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public String toString() {
        return "AnotherFigure {" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                "}\n";
    }
}
