package ru.jrp;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FirstTask {
    public static void main(String[] args) {
        int num = 3;
        String res = cycleGrayCode(num).mapToObj(Integer::toString).collect(Collectors.joining(", "));
        System.out.println(res);
    }

    private static IntStream cycleGrayCode(int number) {
        if( number <= 0 || number > 16) {
            return IntStream.of(0);
        }


        int count = 1 << number;

        return IntStream.range(0, count)
                .map(i -> i ^ (i >> 1));
    }
}
