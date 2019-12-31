package lec08;


import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamEx3 {
    public static void main(String[] args) {
        System.out.println("toList : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toList()));

        System.out.println("toList(distinct) : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(toList()));

        System.out.println("toSet : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toSet()));

        System.out.println("joining : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(joining(", ", "[", "]")));



    }
}
