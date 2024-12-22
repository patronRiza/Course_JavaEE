package jrp.figures;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Figure {
    protected transient String name;
    protected int positionX;
    protected int positionY;

    protected Figure(String name, int positionX, int positionY) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Double getPerimeter() {
        return 0d;
    }

    @Override
    public String toString() {
        return "Figure {" +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                "}\n";
    }
}
