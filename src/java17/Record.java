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
 * 추가로 JPA Entity에는 사용하면 안되지만(프록시 생성을 위해 final 사용 금지, 필수 명명 규칙을 따르지 않음)
 * Dto에는 유용함 record+ builder 는 추가 라이브러리를 사용해야한다한다.
 * 하지만 기본 생성자는 만들어주니 Dto에 적합할듯하다.
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
