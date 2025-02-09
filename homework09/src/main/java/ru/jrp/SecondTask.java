package ru.jrp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondTask {
    public static void main(String[] args) {


        String firstTest = "firstTestText.txt";
        String secondTest = "secondTestText.txt";

        try (Scanner sc = new Scanner(System.in)) {
            test(firstTest);
            test(secondTest);
        }
    }

    private static void test(String fileName) {
        try (InputStream inputStream = SecondTask.class.getClassLoader().getResourceAsStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             FileWriter fileWriter = new FileWriter("result.txt", true);) {

            int top = 10;

            Stream<String> lines = bufferedReader.lines();
            Map<String, Long> wordsCount = lines
                    .flatMap(line -> Arrays.stream(line.split("[^\\p{L}0-9]+")))
                    .filter(word -> !word.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            String res = wordsCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .limit(top)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.joining(", "));

            System.out.println(res);

            fileWriter.write(res + "\n");
            System.out.println("Результат успешно записан в файл");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
