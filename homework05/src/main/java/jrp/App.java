package jrp;

import com.google.gson.Gson;
import jrp.figures.Ellipse;
import jrp.figures.Rectangle;
import jrp.figures.template.TemplateJSON;
import jrp.interfaces.Moveable;
import jrp.utils.JSONSimpleParser;

import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        TemplateJSON object = JSONSimpleParser.parse();

        //Figures start points and sizes
        System.out.println("All figures:\n" + object + "\n\n");

        System.out.println("Get ellipse's perimeter");
        Ellipse ellipse = (Ellipse) object.getFigures().get("ellipse");
        System.out.println(ellipse.getPerimeter() + "\n\n");

        System.out.println("Get rectangle's perimeter");
        Rectangle rectangle = (Rectangle) object.getFigures().get("rectangle");
        System.out.println(rectangle.getPerimeter() + "\n\n");


        System.out.println("Move square on random points");
        Moveable square = (Moveable) object.getFigures().get("square");
        square.moveRandom();
        System.out.println(square + "\n\n");

        System.out.println("Move circle on define points");
        Moveable circle = (Moveable) object.getFigures().get("circle");
        circle.moveFigure(-100, 50);
        System.out.println(circle + "\n\n");

        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("/app/FiguresAfterChange.json")) {
            gson.toJson(object, fileWriter);
            System.out.println("Changes written to file successfully");
        }
    }
}
