package java11;

public class Switch {
    public static void main(String[] args) {
        String a = "A";

        // 기존  case: 와 break 문을 대처
        // 화살표 뒤에 단일 명령문만 break; 생략가능
        // 여러줄이면 써줘야함
        int number = switch(a){
            case "A" -> 0;
            case "B" -> {
                System.out.println("2");
                System.out.println("3");
                int k=3 ;
                yield k; // 반환값은 yield 사용
            }
            default -> 3;
        };

        System.out.println(number);
    }

}
