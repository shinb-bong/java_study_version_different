package java11;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 지역변수 선언시 var로 선언하며 타입을 생략함으로써 가독성을 제고하고 빠른 프로토 타이핑을 위해 사용
 */
public class VarType {
    public static void main(String[] args) throws IOException {
        // 타입 추론
        var list = new ArrayList<String>(); // ArrayList<String>로 추론
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
//        var bos = new ByteArrayInputStream();
//        bos.close();

        // forEach 문 작성시 타입 추론을 이용해 생산성 확장
        for (var element : list) {
            System.out.println("추론하기");
        }

        /**
         * 초기화가 꼭 필요하고
         * null로 초기화 절대 안됨
         * 여러 var 변수를 한꺼번에 초기화 불가능
         * 추론된 타입말고 다른 타입으로 재사용 불가능
         * 지역변수에서만 사용가능
         * 파라미터 리턴타입 사용불가
         * 클래스나 인터페이스명으로 사용 불가
         */
    }

}
