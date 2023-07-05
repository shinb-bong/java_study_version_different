package java8.stream;

import java8.Food;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java8.stream.FoodStream.*;

/**
 * 스트림 연산 추가 작성
 * 생성 -> 중간 연산 -> 최종 연산
 */
public class StreamTest {

    public static void main(String[] args) {

        /**
         * 스트림의 생성
         */
        // 임의의 수에 대한 스트림
        IntStream ints = new Random().ints();
        LongStream longs = new Random().longs();

        // 특정 범위의 연속된 정수
        IntStream intStream = IntStream.range(1, 5);// 1,2,3,4
        IntStream intStream2 = IntStream.rangeClosed(1, 5);// 1,2,3,4,5

        // 특정 범위의 난수
        IntStream ints1 = new Random().ints(10, 1, 10); // (streamSize, begin, end)

        // 람다식 스트림
        Stream.iterate(0,n->n+2)
                .limit(4)
                .forEach(System.out::println); // 0,2,4,6

        /**
         * 스트림의 중간연산
         */
        //distinct() => 중복되는 데이터 제거
        long count = makeFoodList().stream().distinct().count();

        //filter 조건에 맞는 데이터만 남긴다.
        makeFoodList().stream()
                .filter(e -> e.getName().equals("FlatBread"))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        //map
        // 2차원 배열은 flatMap으로 평탄화 시켜야한다.
        List<String> collect = makeFoodList().stream()
                .map(e -> e.getName() + 1)
                .collect(Collectors.toList());
        System.out.println(collect);

        /**
         * 스트림 최종연산
         */
        // forEach
        // 스트림의 데이터를 소모하며 주로 출력하는 용도로 사용
        Stream.iterate(0,n->n+2)
                .limit(4)
                .forEach(System.out::println); // 0,2,4,6

        // allMatch -> 모든 요소 일치 true
        // anyMatch -> 하나의 요소 일치 true
        // noneMatch -> 모든 요소 불일치 true
        boolean flatBread = makeFoodList().stream()
                .map(Food::getName)
                .anyMatch(name -> name.equals("FlatBread"));
        System.out.println("List has FlatBread = " + flatBread);

        // findFirst => 일치하는 첫번째 요소 반환
        // findAny  => 일치하는 요소를 하나 반환
        Food firstFood = makeFoodList().stream()
                .filter(food -> food.getName().startsWith("Flat"))
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        Food anyFood = makeFoodList().stream()
                .filter(food -> food.getName().startsWith("Flat"))
                .findAny()
                .orElseThrow(IllegalStateException::new);

        System.out.println("anyFood = " + anyFood.getName());
        System.out.println("firstFood = " + firstFood.getName());

        // collect(Collector collector)
        // 스트림 요소를 수집해 원하는 형태로 반환

        // collect 는 최종연산 메소드
        // Collector 은 인터페이스
        // Collectors 이미 정의된 컬렉터를 static 메서드로 제공


        //list
        makeFoodList().stream()
                .map(Food::getName)
                .collect(Collectors.toList());
        // map
//        Map<String, Food> collect1 = makeFoodList().java8.stream()
//                .collect(Collectors.toMap(food -> food.getName(), food -> food));// key, value + 현재 중복키 발생가능
        Map<String, Food> collect3 = makeFoodList().stream()
                .collect(Collectors.toMap(food -> food.getName(),
                        food -> food, (existing, replacement) -> existing));// key , value 중복키 x , 키가 겹치면 먼저 있는것이 우선
        System.out.println("collect3.get(\"FlatBread\").getCalories() = " + collect3.get("FlatBread").getCalories());

        // collect(Collectors.groupBy())
        // 분류 함수를 인자로 받아서 데이터를 그룹하여 맵을 반환
        Map<Food.Type, List<Food>> collect1 = makeFoodList().stream().collect(Collectors.groupingBy(food -> food.getType()));
        collect1.forEach((type, foods) -> System.out.println("type = " + type+ " foods= "+ foods.stream().map(food -> food.getName()).toList()));

        // collect(Collectors.joining())
        // 데이터를 연결하여 반환
        String joinStream = makeFoodList().stream()
                .map(food -> food.getName())
                .collect(Collectors.joining(" "));
        System.out.println("joinStream = " + joinStream);

        // reduce()
        // 스트림 데이터를 줄여 나가면서 연산을 수행하고 합과 개수를 반환
        int sum = IntStream.rangeClosed(1, 5).reduce(0, (number1, number2) -> number1 + number2); // 1,2,3,4,5
        int cnt = IntStream.rangeClosed(1, 5).reduce(0, (number1, number2) -> number1 + 1); //1,2,3,4,5 => 5개
        System.out.println("cnt = " + cnt);
        System.out.println("sum = " + sum);

        // 비추 int -> Integer(Boxing) -> int(unBoxing)
        int streamCalories = makeFoodList().stream()
                .map(food -> food.getCalories())
                .reduce(0, Integer::sum).intValue();


        // 기본형 특화 스트림 (int-> int)
        int caloriesSum = makeFoodList().stream()
                .mapToInt(food -> food.getCalories())
                .sum();
        System.out.println("caloriesSum = " + caloriesSum);

        /**
         * 반복문
         * 1. 지역변수를 읽고 수정할 때
         * 2. return break continue 사용할 때
         *
         * 스트림
         * 연속된 데이터(컬렉션)을 "일관","필터" "하나의 연산" "특정 조건" 을 사용할때
         */
    }
}
