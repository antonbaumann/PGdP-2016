package pgdp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class FruitTest {

    public static double isCorrectFruit() {
        double points = 0.0;

        Fruit f = new Fruit();
        if (!f.isApple() && !f.isBanana() && !f.isPineapple()) points += 0.5;
        else System.out.println("Error in Fruit.isApple()/isBanana()/isPineapple()");

        Apple a = new Apple();
        if (a.isApple() && !a.isBanana() && !a.isPineapple()) points += 0.5;
        else System.out.println("Error in Apple.isApple()/isBanana()/isPineapple()");

        GrannySmith g = new GrannySmith();
        if (g.isApple() && !g.isBanana() && !g.isPineapple()) points += 0.5;
        else System.out.println("Error in GrannySmith.isApple()/isBanana()/isPineapple()");

        PinkLady l = new PinkLady();
        if (l.isApple() && !l.isBanana() && !l.isPineapple()) points += 0.5;
        else System.out.println("Error in PinkLady.isApple()/isBanana()/isPineapple()");

        Banana b = new Banana();
        if (!b.isApple() && b.isBanana() && !b.isPineapple()) points += 0.5;
        else System.out.println("Error in Banana.isApple()/isBanana()/isPineapple()");

        Pineapple p = new Pineapple();
        if (!p.isApple() && !p.isBanana() && p.isPineapple()) points += 0.5;
        else System.out.println("Error in Pineapple.isApple()/isBanana()/isPineapple()");

        return points;
    }

    public static double isCorrectShelfLife() {
        Fruit f = new Fruit();
        Apple a = new Apple();
        GrannySmith g = new GrannySmith();
        PinkLady l = new PinkLady();
        Banana b = new Banana();
        Pineapple p = new Pineapple();

        if (f.shelfLife() == -1 && a.shelfLife() == 30 && g.shelfLife() == 50 && l.shelfLife() == 20 && b.shelfLife() == 7 && p.shelfLife() == 20)
            return 1.0;
        else
            return 0.0;
    }

    public static Fruit getRandomFruit() {
        switch (new Random().nextInt(5)) {
            case 0: return new Apple();
            case 1: return new GrannySmith();
            case 2: return new PinkLady();
            case 3: return new Banana();
            case 4: return new Pineapple();
            default: throw new IllegalArgumentException();
        }
    }

    public static double testGetApples() {
        List<Apple> list = new LinkedList<>();
        FruitBasket basket = new FruitBasket();

        IntStream.range(0, 10_000).forEach(i -> {
            Fruit f = getRandomFruit();
            basket.addFruit(f);

            if (f instanceof Apple) list.add((Apple) f);
        });

        List<Apple> list2 = basket.getApples();

        if (list.containsAll(list2) && list2.containsAll(list)) return 2.0;
        else {
            System.out.println("Error in FruitBasket.getApples()");
            return 0.0;
        }
    }

    public static boolean testGetEqualOrLongerShelfLife(int l) {
        List<Fruit> list = new LinkedList<>();
        FruitBasket basket = new FruitBasket();

        IntStream.range(0, 1_000).forEach(i -> {
            Fruit f = getRandomFruit();
            basket.addFruit(f);

            if (f.shelfLife() >= l) list.add(f);
        });

        List<Fruit> list2 = basket.getEqualOrLongerShelfLife(l);

        if (list.containsAll(list2) && list2.containsAll(list)) return true;
        else {
            System.out.println("Error in FruitBasket.getEqualOrLongerShelfLife(int l) with l=" + l);
            return false;
        }
    }

    public static double testGetEqualOrLongerShelfLife() {
        double points = 0.0;

        if (IntStream.range(0, 100).allMatch(x
                -> {
            int n = new Random().nextInt(10_000);
            return testGetEqualOrLongerShelfLife(n);
        }))
            ++points;

        if (IntStream.range(0, 100).allMatch(x
                -> {
            int n = new Random().nextInt(10_000) * -1;
            return testGetEqualOrLongerShelfLife(n);
        }))
            ++points;

        return points;
    }

    public static void main(String[] args) {
        double points = 0.0;

        points += isCorrectFruit();
        points += isCorrectShelfLife();
        points += testGetApples();
        points += testGetEqualOrLongerShelfLife();

        System.out.println("You received " + points + " points. You have another chance of getting additional 2 points, if you implemented isApple/isBanana/isPineapple only whenever needed.");
    }
}
