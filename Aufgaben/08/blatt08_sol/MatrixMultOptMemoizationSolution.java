
public class MatrixMultOptMemoizationSolution {

    public static int min(int n, int m) {
        return n > m ? m : n;
    }

    public static int f(int[][] mm) {
        return f(mm, 0, mm.length - 1, new int[mm.length][mm.length]);
    }

    public static int f(int[][] mm, int i, int j, int[][] memo) {
        if (i > j) throw new IllegalArgumentException();

        if (memo[i][j] != 0) return memo[i][j];

        if (i == j) {
            return 0;
        } else {
            int min = Integer.MAX_VALUE;

            for (int k = i; k < j; ++k) {
                min = min(min, f(mm, i, k, memo) + f(mm, k + 1, j, memo) + (mm[i][0] * mm[k][1] * mm[j][1]));
            }

            return memo[i][j] = min;
        }
    }

}
