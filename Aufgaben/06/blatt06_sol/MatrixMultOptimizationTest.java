
import java.util.Arrays;

public class MatrixMultOptimizationTest {

    public static void main(String[] args) {
        int points = 0;
        int[][] m;
        int i;
        int j;

        m = new int[][]{{10, 30}, {30, 5}, {5, 60}};
        i = 4_500;
        j = MatrixMultOptimization.f(m);
        if (i == j) ++points;
        else System.out.println("Input: " + Arrays.deepToString(m) + " expected output " + i + " your result " + j);

        m = new int[][]{{10, 50}, {50, 10}, {10, 50}};
        i = 10_000;
        j = MatrixMultOptimization.f(m);
        if (i == j) ++points;
        else System.out.println("Input: " + Arrays.deepToString(m) + " expected output " + i + " your result " + j);

        m = new int[][]{{50, 10}, {10, 20}, {20, 5}};
        i = 3_500;
        j = MatrixMultOptimization.f(m);
        if (i == j) ++points;
        else System.out.println("Input: " + Arrays.deepToString(m) + " expected output " + i + " your result " + j);

        m = new int[][]{{2, 10}, {10, 5}, {5, 100}, {100, 2}, {2, 20}};
        i = 1_200;
        j = MatrixMultOptimization.f(m);
        if (i == j) ++points;
        else System.out.println("Input: " + Arrays.deepToString(m) + " expected output " + i + " your result " + j);

        System.out.println("You received " + points + " points.");
    }
}
