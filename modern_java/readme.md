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

- lombok 사용을 통해 생성자, getter, setter 등을 annotation 으로 대체 가능
    - 코드에서 필요한 부분에 집중할 수 있어.
    - @Data annotation
    
#### 모던 자바 (자바8) 못다한 이야기 - 08 Stream API - 01 시작하기전에

- function
    - input이 달라도 같은 output값을 같는 건 가능
    - 하지만 input은 같은데 output값이 다른건 안된다

#### 모던 자바 (자바8) 못다한 이야기 - 08 Stream API - 02 Identity Function

- identity function 
    - method내부에서 null인 경우 그대로 반환해야 하는 경우 사용 가능. (정확히 이해 못함)
    
#### 모던 자바 (자바8) 못다한 이야기 - 08 Stream API - 03 Stream API 01 - 무한 collection

#### 모던 자바 (자바8) 못다한 이야기 - 08 Stream API - 03 Stream API 02 - Stream vs 예전방식
- 필요한 조건들에 맞는 값을 방식을 이전방식과 stream을 사용한 방식을 비교
- 속도, 성능면에서 문제는 없을까? 효율적으로 처리.
- Stream : 게으른 collection Builder

#### 모던 자바 (자바8) 못다한 이야기 - 08 Stream API - 03 Stream API 03 - 기초
- Stream은 lazy evaluation.
- auto boxing, unboxing
    - Integer.valueOf() 를 사용해서 boxing  (new Integer(i) 아님)
        - valueOf method를 보면 내부에서 Integer값을 Caching 하고, 그 값을 반환 (기본적으로 -128 ~ 127까지)
    - `Integer.valueOf(127) == new Integer(127)` 은 true
    - `Integer.valueOf(128) == new Integer(128)` 을 해보면 false 결과가 나옴. 
    - `==` 이 아니라 `equals`로 값을 비교해야함.
        - == : 동일한 객체인지 확인 (메모리 주소까지)
        - equals : 값이 같은지 비교
        
#### 모던 자바 (자바8) 못다한 이야기 - 08 Stream API - 03 Stream API 03A - IDE 활용 팁

#### 모던 자바 (자바8) 못다한 이야기 - 08 Stream API - 03 Stream API 04 - 좀더 실용적인 예
