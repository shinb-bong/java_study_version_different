package java8;

public interface DefaultMethod {
    String returnHello(String msg);
    //구현체 상관없이 공통 기능을 제공할 수가 있다.
    default void hello(String msg){
        System.out.println("hello "+ msg);
    }
}
