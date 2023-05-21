package java11;

import java.util.stream.Stream;

public class StringMethod {
    public static void main(String[] args) {

        String str = "hello";
        System.out.println(str.repeat(3));

        str = "\n\t    hello world \u2005";
        System.out.println(str.strip() + "-end"); // 문자열 앞의 공백을 제거
        System.out.println(str.stripTrailing()+  "-end"); // 문자열 뒤의 공백을 제거

        String str1 = "";
        String str2 = " ";

        System.out.println(str1.isBlank()); // true
        System.out.println(str2.isBlank()); // true

        System.out.println(str1.isEmpty()); //false
        System.out.println(str2.isEmpty()); //false

        // 줄바꿈을 기준으로 분할된 스트림을 반환
        String multilineStr=  "Where shoud I \n go \n !!!!!";
        System.out.println("===== show lines for stream");
        Stream<String> stream =multilineStr.lines();
        stream.forEach(System.out::println);
        System.out.println("=====");
        System.out.println( multilineStr.lines().count());// 스트림은 한번밖에 읽지 못함

        // 들여쓰기 시행
        System.out.println("===== indent");
        String indentedS = multilineStr.indent(3);
        System.out.println(indentedS + "--length:" + indentedS.length());
        System.out.println("=====");

        // 문자열 변환
        String s = "test code";
        String upper = s.transform(String::toUpperCase);
        System.out.println(upper);

        // 포맷 넣기
        System.out.println("hello %s".formatted("coodori"));

        // TextBlock
        // 블록 내의 이스케이프 시퀀스 사용은 가능하나 권장 X
        // 쌍따옴표 3개 연속 사용시 익스케이프 처리 필요
        String html = """
                <html>
                    <body>
                    </body>
                </html>
                """;
        System.out.println(html);
    }
}
