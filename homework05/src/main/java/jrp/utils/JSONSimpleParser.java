package jrp.utils;


import jrp.figures.*;
import jrp.figures.template.TemplateJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public abstract class JSONSimpleParser implements IParser {

    private JSONSimpleParser() {
    }

    public static TemplateJSON parse() {
        TemplateJSON templateJSON = new TemplateJSON();
        JSONParser parser = new JSONParser();


        try (FileReader reader = new FileReader("/app/FiguresStart.json")) {
            JSONObject template = (JSONObject) parser.parse(reader);

            JSONArray jsonArray = (JSONArray) template.get(MAIN_TAG);

            Map<String, Figure> listFigures = new HashMap<>();

            for (Object item : jsonArray) {

                JSONObject figure = (JSONObject) item;

                String figureType = figure.get(FIGURE_TAG).toString();
                long startX = (long) figure.get(START_POSITION_X_TAG);
                long startY = (long) figure.get(START_POSITION_Y_TAG);
                long sizeX = (long) figure.get(SIZE_X_TAG);
                long sizeY = (long) figure.get(SIZE_Y_TAG);

                listFigures.put(figureType, cast(figureType, (int) startX, (int) startY, (int) sizeX, (int) sizeY));


            }

            templateJSON.setFigures(listFigures);

            return templateJSON;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private static Figure cast(String type, int startX, int startY, int sizeX, int sizeY) {
        return switch (type) {
            case "ellipse" -> new Ellipse(type, startX, startY, sizeX, sizeY);
            case "circle" -> new Circle(type, startX, startY, sizeX, sizeY);
            case "rectangle" -> new Rectangle(type, startX, startY, sizeX, sizeY);
            case "square" -> new Square(type, startX, startY, sizeX, sizeY);
            default -> new AnotherFigure(type, startX, sizeY, sizeX, sizeY);
        };
    }
}
