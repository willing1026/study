package lec08;

import java.util.stream.IntStream;

public class StreamEx1 {
    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> System.out.println(i));

        IntStream.iterate(1, i -> i + 1).forEach(i -> System.out.println());
    }
}
