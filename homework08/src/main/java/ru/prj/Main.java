package ru.prj;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = objects -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;

        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

        System.out.println("Количество букв в слове Java: " + safeStringLength.apply("Java"));
        System.out.println("Количество букв в null: " + safeStringLength.apply(null));
    }

    private static <T, R> Function<T, R> ternaryOperator(Predicate<? super T> condition,
                                                         Function<? super T, R> ifTrue,
                                                         Function<? super T, R> ifFalse) {
        return param ->
                condition.test(param) ? ifTrue.apply(param) : ifFalse.apply(param);
    }
}
