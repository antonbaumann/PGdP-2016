package pgdp;

import java.util.LinkedList;
import java.util.List;

public class FruitBasket {

    private final List<Fruit> fruits = new LinkedList<>();

    public void addFruit(Fruit f) {
        fruits.add(f);
    }

    public List<Apple> getApples() {
        List<Apple> list = new LinkedList<>();

        for (Fruit f : fruits) {
            if (f.isApple()) list.add((Apple) f);
        }

        return list;
    }

    public List<Fruit> getEqualOrLongerShelfLife(int n) {
        List<Fruit> list = new LinkedList<>();

        for (Fruit f : fruits) {
            if (f.shelfLife() >= n) list.add(f);
        }

        return list;
    }
}
