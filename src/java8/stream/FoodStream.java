package java8.stream;

import java8.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java 8 stream 예제, 더블콜론 람다식 예제 설명
 * 선언형: 더 간결하고 가독성이 좋아짐
 * 함수의 조립: 유연성이 좋아짐
 * 병렬화: 성능이 좋아짐
 */
public class FoodStream {
    public static void main(String[] args) {
        List<Food> foodList = makeFoodList();
        // foodList ->filter -> sorted -> map -> collect
        List<String> highCaloriesFoodName = foodList.stream() // 소스 가져오기
                .filter(food -> food.getCalories() > 400) // 필터 중간연산
                .map(Food::getName)// 가져올것 중간연산
                .limit(3) // 중간연산
                .collect(Collectors.toList());//collect 최종연산 => 해당이 수행되기 전까지 중간 연산 수행 X

        System.out.println(highCaloriesFoodName);

        // 스트림은 반복의 일회성이다.
        Stream<Food> stream = foodList.stream();
        stream.forEach(System.out::println);
//        stream.forEach(System.out::println); // 에러발생


        /**
         * 컬렉션은 foreach를 사용하여 반복문을 직접명시 => 외부 반복
         * 스트림은 라이브러리 이용 => 내부반복
         */
        List<String> foodNameList =  new ArrayList<>(); // 컬렉션이용
        for (Food food:foodList) {
            foodNameList.add(food.getName());
        }

        // 더블 콜론은 람다의 간결한 버전중 하나이며
        // 스태틱메소드, 인스턴스의 메소드의 메소드 레퍼런스로 사용가능
        List<String> foodNameList2 =  foodList.stream()
//                .map(foodObject -> foodObject.getName())
                .map(Food::getName)
                .collect(Collectors.toList());// 스트림

        /**
         * 최종연산 => 해당이 수행되기 전까지 중간 연산 수행 X
         * filter된 것들은 애초에 map에서 보이지 않음
         */
        List<Food> foodList2 = makeFoodList();
        // foodList ->filter -> sorted -> map -> collect
        List<String> highCaloriesFoodName2 = foodList.stream() // 소스 가져오기
                .filter(food -> {
                    System.out.println("filter= " + food.getName());
                    return food.getCalories() > 400;
                }) // 필터 중간연산
                .map(food -> {
                    System.out.println("map = " + food.getName());
                    return food.getName();
                })// 가져올것 중간연산
                .limit(3) // 중간연산
                .collect(Collectors.toList());//collect 최종연산 => 해당이 수행되기 전까지 중간 연산 수행 X

        System.out.println(highCaloriesFoodName2);

        /**
         * 요약:
         * 스트림을 이용하기에는 3가지 단계
         * 1. 질의를 수행할 데이터 소스(컬렉션)
         * 2.스트림 파이프라인을 구성할 중간 연산
         * 3. 스트림 연산을 실행하고 결과로 출력할 최종연산
         */

    }

    public static List<Food> makeFoodList() {
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("FlatBread",true,400,Food.Type.OTHER));
        foodList.add(new Food("OnionSoup",true,300,Food.Type.OTHER));
        foodList.add(new Food("LobsterRisotto",false,520,Food.Type.FISH));
        foodList.add(new Food("CaesarSalad",true,200,Food.Type.OTHER));
        foodList.add(new Food("BeefWellington",false,670,Food.Type.MEAT));
        foodList.add(new Food("FiletMignon",false,600,Food.Type.MEAT));
        foodList.add(new Food("CrispySalmon",false,620,Food.Type.FISH));
        foodList.add(new Food("StripSteak",false,740,Food.Type.MEAT));
        foodList.add(new Food("SearedScallops",false,340,Food.Type.FISH));
        return foodList;
    }
}
