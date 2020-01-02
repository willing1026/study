package lec08;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class StreamEx4 {
    public static void main(String[] args) {

        //option + enter -> static import 선택
        Stream.of(1, 2, 3, 4, 5)
                .collect(toList());

        Stream.of(1, 2, 3, 4, 5)
                .collect(toSet());
    }
}
