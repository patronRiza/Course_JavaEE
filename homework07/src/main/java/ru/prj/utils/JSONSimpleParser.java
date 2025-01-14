package ru.prj.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.prj.Figure;

import java.io.FileReader;

import static ru.prj.App.figures;

public class JSONSimpleParser implements IParser {

    private JSONSimpleParser() {
    }

    public static void parse() {
        JSONParser parser = new JSONParser();


        try (FileReader reader = new FileReader("Figures.json")) {
            JSONObject template = (JSONObject) parser.parse(reader);

            JSONArray jsonArray = (JSONArray) template.get(MAIN_TAG);

            for (Object item : jsonArray) {

                JSONObject figure = (JSONObject) item;

                String name = figure.get(FIGURE_TAG).toString();
                Long startX = (Long) figure.get(START_POSITION_X_TAG);
                Long startY = (Long) figure.get(START_POSITION_Y_TAG);
                Long sizeX = (Long) figure.get(SIZE_X_TAG);
                Long sizeY = (Long) figure.get(SIZE_Y_TAG);

                figures.put(name, new Figure(name, startX, startY, sizeX, sizeY));


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
