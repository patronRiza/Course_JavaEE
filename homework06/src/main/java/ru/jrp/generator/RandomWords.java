package ru.jrp.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static ru.jrp.Main.rand;

public class RandomWords {

    private RandomWords() {}

    public static void generate() {
        // Количество слов
        int numberOfWords = 20;

        // Длина каждого слова
        int wordLength = 5;

        // Генерация случайных слов, разделенных запятой
        String randomWords = generateRandomWords(numberOfWords, wordLength);

        // Запись строки в файл
        try (FileWriter writer = new FileWriter("inputDataOfWords.txt")) {
            writer.write(randomWords);
            System.out.println("Файл успешно создан");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String generateRandomWords(int numberOfWords, int wordLength) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numberOfWords; i++) {
            // Генерация случайного слова
            String randomWord = generateRandomWord(rand, wordLength);
            sb.append(randomWord);

            // Добавление запятой, если это последнее слово
            if (i < numberOfWords - 1) {
                sb.append(",\n");
            }
        }
        return sb.toString();
    }

    private static String generateRandomWord(Random random, int wordLength) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // Буквы для генерации слов
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            // Выбираем случайный символ из строки characters
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}
