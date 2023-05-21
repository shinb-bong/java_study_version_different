package java17;

import java.util.List;

/**
 * java 16 에 나온 기술
 * .collect(Colleters.toList()); 를 더욱 간편하게 사용
 */
public class ToList {
    public static void main(String[] args) {
        List<Integer> result = List.of(1, 2, 3, 4, 5,6).stream()
                .filter(i -> i > 4)
                .toList();

        result.forEach(System.out::println);
    }
}
