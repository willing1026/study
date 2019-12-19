### [모던자바 못다한 이야기-케빈TV](https://goo.gl/3XjAVf) 실습 프로젝트

#### 모던 자바 (자바8) 못다한 이야기 - 07 실전 예제와 Functional Interface의 제약 사항
- functional interface의 method 인자, 리턴타입이 제네릭인 경우, 사용 불가

```
#Target method is generic. (error)
functional interface에 Type이 선언되어있지 않은데, method가 generic일 경우

final lec07.InvalidFunctionalInterface invalidFunctionalInterface = value -> value.toString();
System.out.println(invalidFunctionalInterface.mkString(123));


@FunctionalInterface
interface lec07.InvalidFunctionalInterface {
    <T> String mkString(T value);
}
```

- 다음번 17:45 부터