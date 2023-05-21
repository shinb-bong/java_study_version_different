package java8.functionalInterface;

/**
 * 단 하나의 추상메서드를 갖는 인터페이스를 함수형 인터페이스라고 지칭
 * ex) Runnable
 * 컴파일 시점에 확인이 가능하도록 어노테이션 제공
 */
@FunctionalInterface
public interface Cat {
    String name(String name);
}
