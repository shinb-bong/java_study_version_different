package java8.functionalInterface;



public class Cat_Starter {
    public static void main(String[] args) {
        // 기존 방식
        Cat cat = new Cat(){
            @Override
            public String name(String name) {
                return "고양이 이름은 "+ name + "입니다.";
            }
        };

        // 람다 방식
        // 하나밖에 없음
        Cat cat2 = (name) -> "고양이 이름은 "+ name + "입니다.";
        System.out.println(cat.name("고냥이"));
        System.out.println(cat2.name("뚱냥이"));
    }



}



