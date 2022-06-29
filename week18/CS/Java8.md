## Java8

---------------

### [함수형 인터페이스]

1. 함수형 인터페이스(Functional interface)

   - 딱 **한 개의 추상 메소드**를 갖는 인터페이스

   - SAM (Single Abstract Method)이라고도 불림

   - **@FunctionalInterface** 애노테이션을 갖고 있는 인터페이스

```java
public interface FunctionalInterface {
    public abstract void doSomething(String text);
}
```



2. 함수형 인터페이스를 사용하는 이유

   - 자바의 **람다식은 함수형 인터페이스로만 접근 가능**하기 때문

   - 기존의 익명클래스를 활용한 방법을 **''함수형 인터페이스 + 람다식''**으로 간단하게 표현함

```java
// 1. 함수형 인터페이스 + 람다식 (편--------안)
public interface FunctionalInterface { // 함수형 인터페이스
     public abstract void doSomething(String text);
}

FunctionalInterface func = text -> System.out.println(text); // 람다식
func.doSomething("do something");
// 실행 결과
// do something
```

```java
// 2. 익명 클래스를 활용한 방법 (불---------편)
FunctionalInterface func = new FunctionalInterface() {
    @Override
    public void doSomething(String text) {
        System.out.println(text);
    }
};
func.doSomething("do something");
```



------------------

### [자바에서 제공하는 함수형 인터페이스]

- Function<T, R> : T타입을 받아서 R타입을 리턴
- BiFunction<T, U, R> : **두 개의 값 (T, U)를 받아서 R타입을 리턴**
- Consumer<T> : T타입을 받아서 아무 값도 리턴하지 않음
- Supplier<T> : T타입의 값을 제공(리턴)
- Predicate<T> : T타입을 받아서 boolean을 리턴
- UnaryOperator<T> : Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 **동일한 타입**을 리턴
- BinaryOperator<T> : BiFunction<T, U, R>의 특수한 형태로, **동일한 타입**의 입력값 두 개를 받아 리턴
- Runnable : 인자를 받지 않고 리턴 값도 없음



1. Function<T, R>

   - **T 타입 인자를 받아서 R타입 객체를 리턴**하는 함수형 인터페이스

     ```java
     public interface Function<T, R> {
         R apply(T t);
     
         default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
             Objects.requireNonNull(before);
             return (V v) -> apply(before.apply(v));
         }
     
         default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
             Objects.requireNonNull(after);
             return (T t) -> after.apply(apply(t));
         }
     
         static <T> Function<T, T> identity() {
             return t -> t;
         }
     }
     ```

     - R **apply**(T t)

     ```java
     // 1. apply()
     Function<Integer, Integer> multiply = (value) -> value * 2;
     Integer result = multiply.apply(3); // apply 내장 메소드를 사용하면 람다 표현식으로 저장된 함수가 실행됨
     System.out.println(result);
     // 결과
     // 6
     ```

   - 함수 조합용 메소드

     - **compose()** : 두 개의 Function을 조합하여 새로운 Function 객체를 만들어주는 메소드
     - **andThen()** : compose()와 실행 순서만 반대

     ```java
     // 2. compose()
     Function<Integer, Integer> multiply = (value) -> value * 2;
     Function<Integer, Integer> add      = (value) -> value + 3;
     
     // compose 메소드가 두 개의 Function을 조합 (add -> multiply 순서)
     Function<Integer, Integer> addThenMultiply = multiply.compose(add);
     
     Integer result1 = addThenMultiply.apply(3);
     System.out.println(result1);
     // 결과
     // 12
     ```

     

2. Runnable

   - **인자를 받지 않고 리턴값도 없는** 함수형 인터페이스

     - **void run()**

     ```java
     public interface Runnable {
       public abstract void run();
     }
     ```

   - Runnable은 **run()** 메소드를 호출해야 함

     ```java
     Runnable runnable = () -> System.out.println("run anything!"); // 인자無, 리턴無
     runnable.run();
     // 결과
     // run anything!
     ```

     

3. Supplier<T>

   - **T타입의 값을 제공**하는 함수형 인터페이스

     -  **T get()**

     ```java
     public interface Supplier<T> {
         T get();
     }
     ```

   - Supplier는 **get()** 메소드를 호출해야 함

     ```java
     Supplier<String> getString = () -> "Happy new year!";
     String str = getString.get();
     System.out.println(str);
     // 결과
     // Happy new year!
     ```



4. Consumer<T>

   - **T타입을 받아서 아무 값도 리턴하지 않는** 함수형 인터페이스

     ```java
     public interface Consumer<T> {
         void accept(T t);
     
         default Consumer<T> andThen(Consumer<? super T> after) {
             Objects.requireNonNull(after);
             return (T t) -> { accept(t); after.accept(t); };
         }
     }
     ```

     -  **void accept(T t)**

     ```java
     Consumer<String> printString = text -> System.out.println("Miss " + text + "?");
     printString.accept("me");
     // 결과
     // Miss me?
     ```

   - 함수 조합용 메소드

     - **andThen()** : 두 개 이상의 Consumer를 **연속적**으로 실행

     ```java
     Consumer<String> printString = text -> System.out.println("Miss " + text + "?");
     Consumer<String> printString2 = text -> System.out.println("--> Yes");
     printString.andThen(printString2).accept("me");
     // 결과
     // Miss me?
     // --> Yes
     ```



5. Predicate<T>

   - **T 타입을 받아서 boolean을 리턴**하는 함수형 인터페이스

     ```java
     public interface Predicate<T> {
         boolean test(T t);
     
         default Predicate<T> and(Predicate<? super T> other) {
             Objects.requireNonNull(other);
             return (t) -> test(t) && other.test(t);
         }
     
         default Predicate<T> negate() {
             return (t) -> !test(t);
         }
     
         default Predicate<T> or(Predicate<? super T> other) {
             Objects.requireNonNull(other);
             return (t) -> test(t) || other.test(t);
         }
     
         static <T> Predicate<T> isEqual(Object targetRef) {
             return (null == targetRef)
                     ? Objects::isNull
                     : object -> targetRef.equals(object);
         }
     }
     ```

     - **boolean test(T t)**

     ```java
     Predicate<Integer> isBiggerThanFive = num -> num > 5;
     System.out.println("10 is bigger than 5? -> " + isBiggerThanFive.test(10));
     // 결과
     // 10 is bigger than 5? -> true
     ```

   - 함수 조합용 메소드

     - And
     - Or
     - Negate

     ```java
     Predicate<Integer> isBiggerThanFive = num -> num > 5;
     Predicate<Integer> isLowerThanSix = num -> num < 6;
     System.out.println(isBiggerThanFive.and(isLowerThanSix).test(10));
     System.out.println(isBiggerThanFive.or(isLowerThanSix).test(10));
     // 결과
     // false
     // true
     ```

     

-----------------

### [람다 표현식]

- 람다 표현식은 **메서드로 전달할 수 있는 익명 함수를 단순화한 것**이라고 할 수 있다.

- 람다

  - **(인자 리스트) -> { 바디 }**

  <img src="https://blog.kakaocdn.net/dn/bu6kQY/btqXgpcd9oq/3t2snduFt9KbXutBXidlHK/img.png" alt="img" style="zoom: 40%;" />

- 인자 리스트

  - 인자가 없을 때 : ()
  - 인자가 한 개일 때 (괄호 생략 가능) : (one) 또는 one

  - 인자가 여러 개일 때 (괄호 생략 불가) : (one, two) 
  - 인자의 타입은 생략 가능, **컴파일러가 추론(infer)**하지만 명시할 수도 있다
    - (Integer one, Integer two)



- 바디
  - 화살표 오른쪽에 함수 본문을 정의
  - **여러 줄**인 경우에 **{ }를 사용**해서 묶는다
  - 한 줄인 경우에 { } 생략 가능
    - `{}`이 생략되면 **return** 키워드와 **;(세미콜론)**도 같이 생략해야 한다.



- 람다 예제

  - 불리언 표현식

    ```java
      (List<String> list) -> list.isEmpty()
    ```

  - 객체 생성

    ```java
      () -> new Apple(10)
    ```

  - 객체에서 소비

    ```java
      (Apple a) -> {
              System.out.println(a.getWeight());
      }
    ```

  - 객체에서 선택/추출

    ```java
      (String s) -> s.length()
    ```

  - 두 값을 조합

    ```java
      (int a, int b) -> a * b
    ```

  - 두 객체 비교

    ```java
      (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
    ```

    

-------------

### 
