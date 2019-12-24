package lec08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StreamPrelude {
    public static void main(String[] args) {
        final int abs1 = Math.abs(-1);
        final int abs2 = Math.abs(1);

        System.out.println("abs1 == abs2 : " + (abs1 == abs2));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(map(numbers, i -> i*2));

        System.out.println(mapNew(numbers, i -> i)); //내부에서 mapper의 null check 필요없음.
        System.out.println(mapNew(numbers, Function.identity()));
    }

    private static <T, R> List<R> map(final List<T> list, final Function<T, R> mapper) {
        final Function<T, R> function;
        if (mapper != null) {
            function = mapper;
        } else {
            function = t -> (R) t; //identity function 변경중.
        }

        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(function.apply(t));
        }

        return result;
    }

    private static <T, R> List<R> mapNew(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}
