package java8;

/**
 * 출처: https://ksr930.tistory.com/237
 */
public class Food {

    public enum Type{
        MEAT, FISH, OTHER
    }

    private final String name;
    private final boolean isVegetarian;
    private final int calories;
    private final Type type;

    public Food(String name, boolean isVegetarian, int calories, Type type) {
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }
}
