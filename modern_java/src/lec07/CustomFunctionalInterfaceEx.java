package lec07;

import java.math.BigDecimal;

public class CustomFunctionalInterfaceEx {
    public static void main(String[] args) {
        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

        final InvalidFunctionalInterface anonymousClass = new InvalidFunctionalInterface() {
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };

        System.out.println("anonymous class : " + anonymousClass.mkString(123));

        //error!
//        final InvalidFunctionalInterface invalidFunctionalInterface = value -> value.toString();
//        System.out.println(invalidFunctionalInterface.mkString(123));


    }
}

@FunctionalInterface
interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface {
    <T> String mkString(T value);
}
