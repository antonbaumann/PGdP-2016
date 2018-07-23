
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ToolboxTest {

    public static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static int[] randomIntArray() {
        int[] m = new int[new Random().nextInt(100)];

        for (int i = 0; i < m.length; ++i)
            m[i] = new Random().nextInt();

        return m;
    }

    public static int[] randomPosIntArray() {
        int[] m = new int[new Random().nextInt(100)];

        for (int i = 0; i < m.length; ++i)
            m[i] = new Random().nextInt(Integer.MAX_VALUE);

        return m;
    }

    public static int testEvenSum() {
        int points = 0;

        if (Toolbox.evenSum(0) != 0) System.out.println("evenSum(0) != 0");

        if (IntStream.range(0, 1000).allMatch(x
                -> {
            int n = new Random().nextInt(10_000) + 1;
            return n / 2 * (n / 2 + 1) == Toolbox.evenSum(n);
        }))
            ++points;
        else
            System.out.println("evenSum(n) where n > 0 failed");

        if (IntStream.range(0, 1000).allMatch(x
                -> {
            int n = -(new Random().nextInt(10_000) + 1);
            return -(n / 2 * (n / 2 - 1)) == Toolbox.evenSum(n);
        }))
            ++points;
        else
            System.out.println("evenSum(n) where n < 0 failed");

        return points;
    }

    public static int testMultiplication() {
        int points = 0;

        if (IntStream.range(0, 1000).allMatch(x
                -> Toolbox.multiplication(0, random(-10_000, +10_000)) == 0
                && Toolbox.multiplication(random(-10_000, +10_000), 0) == 0))
            ++points;
        else
            System.out.println("multiplication(0,k) or multiplication(k,0) != 0");

        if (IntStream.range(0, 1000).allMatch(x
                -> {
            int n = new Random().nextInt(10_000) + 1;
            int m = new Random().nextInt(10_000) + 1;
            return (n * m) == Toolbox.multiplication(n, m);
        }))
            ++points;
        else
            System.out.println("multiplication(x,y) with x,y > 0 failed");

        if (IntStream.range(0, 1000).allMatch(x
                -> {
            int n = random(-10_000, +10_000);
            int m = random(-10_000, +10_000);
            return (n * m) == Toolbox.multiplication(n, m);
        }))
            ++points;
        else
            System.out.println("multiplication(x,y) with x,y in [-10000,+10000] failed");

        return points;
    }

    public static boolean testReverse(int[] m) {
        int[] n = Arrays.copyOf(m, m.length);
        int[] n_sol = Arrays.copyOf(m, m.length);

        Toolbox.reverse(n);
        ToolboxSolution.reverse(n_sol);

        if (Arrays.equals(n, n_sol)) return true;
        else {
            System.out.println("reverse(" + Arrays.toString(m) + ") = " + Arrays.toString(n) + " but should equal " + Arrays.toString(n_sol));
            return false;
        }
    }

    public static int testReverse() {
        int points = 0;

        if (testReverse(new int[]{})) ++points;

        if (IntStream.range(0, 1000).allMatch(x -> testReverse(randomIntArray())))
            ++points;

        return points;
    }

    public static boolean testNumberOfOddIntegers(int[] m) {
        int n = Toolbox.numberOfOddIntegers(m);
        int n_sol = ToolboxSolution.numberOfOddIntegers(m);

        if (n == n_sol) return true;
        else {
            System.out.println("numberOfOddIntegers(" + Arrays.toString(m) + ") = " + n + " but should equal " + n_sol);
            return false;
        }

    }

    public static int testNumberOfOddIntegers() {
        int points = 0;

        if (testNumberOfOddIntegers(new int[]{}))
            ++points;

        if (IntStream.range(0, 1000).allMatch(x -> testNumberOfOddIntegers(randomPosIntArray())))
            ++points;

        if (IntStream.range(0, 1000).allMatch(x -> testNumberOfOddIntegers(randomIntArray())))
            ++points;

        return points;
    }

    public static boolean testFilterOdd(int[] m) {
        int[] n = Toolbox.filterOdd(m);
        int[] n_sol = ToolboxSolution.filterOdd(m);

        if (Arrays.equals(n, n_sol)) return true;
        else {
            System.out.println("filterOdd(" + Arrays.toString(m) + ") = " + Arrays.toString(n) + " but should equal " + Arrays.toString(n_sol));
            return false;
        }
    }

    public static int testFilterOdd() {
        int points = 0;

        if (testFilterOdd(new int[]{}))
            ++points;

        if (IntStream.range(0, 1000).allMatch(x -> testFilterOdd(randomPosIntArray())))
            ++points;

        if (IntStream.range(0, 1000).allMatch(x -> testFilterOdd(randomIntArray())))
            ++points;

        return points;
    }

    public static void main(String[] args) {
        int points = 0;

        points += testEvenSum();
        points += testMultiplication();
        points += testReverse();
        points += testNumberOfOddIntegers();
        points += testFilterOdd();

        System.out.println("\n\nYou received " + points + " points.");
    }

}
