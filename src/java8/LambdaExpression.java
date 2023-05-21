package java8;

/**
 * java 8 람다 표현식
 */
public class LambdaExpression {

    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        lambda.thread.start();
        lambda.thread2.start();
    }

    static class Lambda {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("Start new thread!");
            }
        });
        // 람다 표현식
        // 파라미터가 없는 경우 빈괄호를 명시적으로 표기
        Thread thread2 = new Thread(()-> System.out.println("Start new thread!"));
    }

    /**
     * 타입은 생략이 가능하며 컴파일러가 알맞은 객체를 선택
     * 파라미터가 한개인 경우에는 괄호 생략 가능
     */

    /**
     * 바디가 짧아 한줄로 끝나면 중괄호 return 생략 가능
     * 두줄 이상 return 명시
     */

}
