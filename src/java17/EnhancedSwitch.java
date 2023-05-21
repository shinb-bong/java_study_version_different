package java17;

/**
 * 타입 제약과 장황한 문법에 보다 좋아진 switch
 */
public class EnhancedSwitch {
    public static void main(String[] args) {
        Object o = "2.00";
        System.out.println(getDoubleUsingSwitch(o));
    }

    private static double getDoubleUsingSwitch(Object o){
        return switch (o){
            case Integer i -> i.doubleValue();
            case Float f -> f.doubleValue();
            case String s -> Double.parseDouble(s);
            default -> 0d;
        };
    }

}
