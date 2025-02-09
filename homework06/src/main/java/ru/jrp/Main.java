package ru.jrp;

import ru.jrp.generator.RandomNumbers;
import ru.jrp.generator.RandomWords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Random rand = new Random();
    public static void main(String[] args) {

        //firstExercise
        //------------------------------
        RandomNumbers.generate();

        //Создайте массив из N чисел
        Integer[] array = createArrayOfIntegers("inputDataOfNumbers.txt");

        //На основе массива создайте список List.
        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        System.out.println(list);

        //Отсортируйте список в натуральном порядке.
        //Collections.sort(list);

        //Отсортируйте список в обратном порядке.
        list.sort(Collections.reverseOrder());

        //Перемешайте список.
        //Collections.shuffle(list);

        //Выполните циклический сдвиг на 1 элемент.
        //cyclicLeftShift(list);
        //cyclicRightShift(list);

        //Оставьте в списке только уникальные элементы.
        //System.out.println(returnUniqueList(list));

        //Оставьте в списке только дублирующиеся элементы.
        System.out.println(returnDublicate(list));


        //Из списка получите массив.
        //replaceInArrya(list);


        //secondExercise
        //------------------------------
        RandomWords.generate();

        //Создать коллекцию HashSet с типом элементов String.
        Set<String> words = new HashSet<>();

        //Добавить в неё 10 строк.
        //read10Words("inputDataOfWords.txt", words);

        //Добавить в множество минимум пять объектов, которые являются
        //совместимыми с типом данных коллекции.
        /*addCorrectInstances(
                Set.of(
                        "gelsa",
                        new Object(),
                        new ArrayList<>(),
                        "asc",
                        "sdc",
                        new Random(),
                        new StringBuilder(),
                        "sdcs",
                        "sdvs"), words);*/

        //Вывести на экран элементы множества используя цикл for.
        /*for (String word : readWords("inputDataOfWords.txt")) {
            System.out.println(word);
        }*/

        //Добавить новый элемент, который уже присутствует в множестве.
        /*words = readWords("inputDataOfWords.txt");
        words.add(words.iterator().next());*/

        //Определить, содержит ли коллекция определенный объект.
        words = readWords("inputDataOfWords.txt");
        System.out.println(words.contains(words.iterator().next()));
        System.out.println(words.contains("kjfhbvsk"));

        //Удалить из коллекции любой объект.
        //words = readWords("inputDataOfWords.txt");
        System.out.println(words);
        System.out.println(words.remove(words.iterator().next()));
        System.out.println(words);

        //Получить количество элементов, содержащихся в коллекции на данный момент.
        /*words = readWords("inputDataOfWords.txt");
        System.out.println(words.size());*/

        //Удалить все элементы множества.
        //words = readWords("inputDataOfWords.txt");
        words.clear();

        //Проверить, является ли коллекция HashSet пустой.
        System.out.println(words.isEmpty());

    }

    private static Integer[] replaceInArrya (List<Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);

        System.out.print("Array: ");
        for (Integer number : array) {
            System.out.print(number + " ");
        }

        return array;
    }

    private static List<Integer> returnDublicate(List<Integer> list) {
        Set<Integer> uniqueElements = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();

        for (Integer number : list) {
            if (!uniqueElements.add(number)) { // Если элемент уже есть в Set, он дублируется
                duplicates.add(number);
            }
        }
        return duplicates;
    }

    private static List<Integer> returnUniqueList(List<Integer> list) {
        List<Integer> uniqueList = new ArrayList<>();
        for (Integer number : list) {
            if (!uniqueList.contains(number)) { // Проверяем, есть ли элемент уже в списке
                uniqueList.add(number); // Добавляем, если его нет
            }
        }
        return uniqueList;
    }

    private static void cyclicLeftShift(List<Integer> list) {
        if (!list.isEmpty()) {
            Integer first = list.removeFirst();
            list.add(first);
        }
    }

    private static void cyclicRightShift(List<Integer> list) {
        if (!list.isEmpty()) {
            Integer last = list.removeLast();
            list.addFirst(last);
        }
    }

    private static Integer[] createArrayOfIntegers(String fileName) {
        String[] stringArray = readNumbersForList(fileName);
        Integer[] integerArray = new Integer[stringArray.length];

        for (int i = 0; i < integerArray.length; i++) {
            integerArray[i] = Integer.parseInt(stringArray[i]);
        }

        return integerArray;
    }

    private static String[] readNumbersForList(String fileName) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String lines;
            while((lines = bufferedReader.readLine()) != null ) {
                return lines.split(", ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String[] {"empty"};
    }

    private static void read10Words(String fileName, Set<String> words) {
        Iterator<String> iterator = readWords(fileName).iterator();
        int count = 0;
        System.out.println("Первые 10 элементов:");
        while (iterator.hasNext() && count < 10) {
            words.add(iterator.next());
            count++;
        }
    }

    private static Set<String> readWords(String fileName) {
        Set<String> wordSet = new HashSet<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordSet.add(line.substring(0, line.length() - 1));
            }
            return wordSet;
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return new HashSet<>();
    }

    private static void addCorrectInstances(Set<?> set, Set<String> words) {
        for (Object object : set) {
            if (object instanceof String s) {
                words.add(s);
            }
        }
    }
}
