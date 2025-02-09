package ru.prj;


import ru.prj.utils.JSONSimpleParser;
import ru.prj.utils.PersonalHashMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {

    public static Map<String, Figure> figures = new PersonalHashMap<>();

    public static void main(String[] args){

        //Добавить в коллекцию минимум пять пар «ключ-значение».
        //Несколько ключей должны иметь одно и то же значение.
        JSONSimpleParser.parse();

        //Выполнить прямой перебор коллекции используя цикл for.
        /*for (Map.Entry<String, Figure> entry : figures.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
        }*/

        //Добавить новый элемент с уже имеющимся в отображении ключом.
        //figures.put("ellipse", new Figure("ellipse", 40L, 35L, 60L, 50L));

        //Вынести список всех ключей из HashMap в отдельную коллекцию.
        Set<String> keys = figures.keySet();

        //Вынести список всех значений из HashMap в коллекцию HashSet и получить
        //количество уникальных значений.
        Set<Figure> uniqueFigures = new HashSet<>(figures.values());
        System.out.println(uniqueFigures.size());

        //Определить, содержит ли коллекция HashMap определенный ключ.
        System.out.println(figures.containsKey("polygon"));
        System.out.println(figures.containsKey("romb"));

        //Определить, содержит ли коллекция HashMap определенное значение.
        System.out.println(figures.containsValue(new Figure("ellipse", 40L, 35L, 60L, 50L)));
        //System.out.println(figures.containsValue(new Figure("ellipse", 41L, 35L, 60L, 50L)));

        //Получить количество элементов, содержащихся в данный момент в коллекции HashMap.
        System.out.println(figures.size());

        //Удалить из коллекции выбранный объект по ключу и по значению.
        System.out.println(figures);
        figures.remove("ellipse", new Figure("ellipse", 40L, 35L, 60L, 50L));
        System.out.println(figures);
    }
}
