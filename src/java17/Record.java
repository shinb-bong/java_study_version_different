package java17;

import java.util.ArrayList;
import java.util.List;

/**
 * 엄밀히 말하면 java 14 version이긴함.
 * 기존에는 하나하나씩
 * Constructor , Setter , equals, hasCode, toString 생성
 * 하지만 record 클래스로 선언하면 Data Carrier 클래스임
 * 개발자에게 데이터 모델링에 집중하게 하는 것
 *
 */
public class Record {
    public static void main(String[] args) {
        Address address = new Address("서울", "신촌", "더움");
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review(2L, 3L, "Good"));
        Poi today = new Poi(1L, "오늘의날씨", address, reviewList);
        System.out.println(today.toString());
    }

    record Poi(long id,
               String title,
               Address oldAddress,
               List<Review> reviewList){
    }

    record Address(String city,
                   String country,
                   String detail){
    }

    record Review(long id,
                  long reviewerId,
                  String comment){

    }
    /**
     * 주의사항
     * 상속불가능 : extends
     * 암묵적 final class, abstract 불가
     * 멤버 변수 선언 불가능
     * Native Method 불가능
     */
}
