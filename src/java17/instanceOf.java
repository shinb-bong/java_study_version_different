package java17;

/**
 * java 16 에 소개된 기술
 * 객체 타입을 비교하고 지역변수로 새롭게 정의하는 번거로운 작업을 줄여주었다.
 * 타입에 일치한다면 타입 캐스팅 작업을 바로 해줌
 * 하지만 || 같이 일수도있다는 조건을 주면 에러가남
 */
public class instanceOf {
    public static void main(String[] args) {
     Object a = "king";
     if (a instanceof String k && k.length() == 4 ){
         System.out.println(k.toUpperCase());
     }
//    if (a instanceof String k || k.length() == 4 ){
//        System.out.println(k.toUpperCase());
//    }
    }
}
