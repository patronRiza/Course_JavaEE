package ru.prj;


import java.util.Objects;

public class Figure {
    private final String name;
    private final Long startX;
    private final Long startY;
    private final Long sizeX;
    private final Long sizeY;

    public Figure(String name, Long startX, Long startY, Long sizeX, Long sizeY) {
        this.name = name;
        this.startX = startX;
        this.startY = startY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Figure figure)) return false;
        return Objects.equals(name, figure.name) && Objects.equals(startX, figure.startX) && Objects.equals(startY, figure.startY) && Objects.equals(sizeX, figure.sizeX) && Objects.equals(sizeY, figure.sizeY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startX, startY, sizeX, sizeY);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "startX=" + startX +
                ", startY=" + startY +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                "}";
    }
}
