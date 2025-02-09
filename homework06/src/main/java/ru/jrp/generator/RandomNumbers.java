package ru.jrp.generator;

import java.io.FileWriter;
import java.io.IOException;

import static ru.jrp.Main.rand;

public class RandomNumbers {

    private RandomNumbers() {}

    public static void generate() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            stringBuilder.append(rand.nextInt(100) + 1);
            if (i < 99) {
                stringBuilder.append(", ");
            }
        }

        try (FileWriter fileWriter = new FileWriter("inputDataOfNumbers.txt")) {
            fileWriter.write(stringBuilder.toString());
            System.out.println("Файл успешно создан");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
