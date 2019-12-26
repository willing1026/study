package lec08;

import java.util.Arrays;
import java.util.List;

public class StreamEx2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        oldVersion(numbers);

        //stream 사용
        System.out.println(
                numbers.stream()
                        .filter(number -> {
                            System.out.println("number > 3");
                            return number > 3;
                        })
                        .filter(number -> {
                            System.out.println("number < 9");
                            return number < 9;
                        })
                        .map(number -> {
                            System.out.println("number * 2");
                            return number * 2;
                        })
                        .filter(number -> {
                            System.out.println("number > 10");
                            return number > 10;
                        })
                        .findFirst()
        );

    }

    private static void oldVersion(List<Integer> numbers) {
        //이전방식
        Integer result = null;
        for (Integer number : numbers) {
            if (number > 3 && number < 9) {
                Integer newNumber = number * 2;

                if (newNumber > 10) {
                    result = newNumber;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
