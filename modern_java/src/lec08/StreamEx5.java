package lec08;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamEx5 {
    public static void main(String[] args) {

        final int[] sum = {0};

        //single core programing
        IntStream.range(0, 100)
                .forEach(i -> sum[0] += i);
        System.out.println("sum: " + sum[0]);

        //parallel programing
        final int[] sum2 = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);

        System.out.println("parallel sum(with side-effect): " + sum2[0]);

        System.out.println("stream sum(without side-effect): " +
                IntStream.range(0, 100)
                        .sum());

        System.out.println("parallel stream sum(without side-effect): " +
                IntStream.range(0, 100)
                        .parallel()
                        .sum());

        //parallel stream 장점
        final long start = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println("parallel stream time : " + (System.currentTimeMillis() - start));

        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println("single stream time : " + (System.currentTimeMillis() - start2));


        //core 개수 설정
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7");
    }

}
