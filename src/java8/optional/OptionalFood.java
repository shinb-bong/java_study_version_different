package java8.optional;

import java8.Food;
import java8.stream.FoodStream;

import java.util.List;
import java.util.Optional;

/**
 * NPE를 방지하기 위한 문법
 * 해당은 자주 사용하였으니 복기하는 느낌으로만
 */
public class OptionalFood {

    public static void main(String[] args) {
        List<Food> foodList = FoodStream.makeFoodList();
        Optional<String> foodName = new BookService().getFoodName(foodList.get(0));
        System.out.println("foodName = " + foodName.orElseThrow(()->new IllegalArgumentException("해당 음식 이름이 없음")));
    }
    static class BookService{
        private Optional<String> getFoodName(Food food){
            return Optional.ofNullable(food)
                    .map(foodObject -> foodObject.getName());
        }
        /**
         * Optional 로 감싸는 메소드
         */
        private void optionalWrapperMethod(Food food){
            Optional.of(food); // 파라미터 null 이면 NPE
            Optional.ofNullable(food); // 파라미터 null이면 return 빈 옵셔널
            Optional.empty(); // 빈 옵셔널
        }

        /**
         * Optional middle wrapper 메소드
         */
        private void optionalMiddleMethod(Food food){
            Optional.ofNullable(food).map(foodObject -> foodObject.getName()); // 스트림과 동일 객체를 변환
            Optional.ofNullable(food).filter(foodObject -> foodObject.isVegetarian()); // 스트림과 동일
        }

        // 결론 메소드는 자주 사용했으므로
        // 복기정도만 한다.
    }
}
