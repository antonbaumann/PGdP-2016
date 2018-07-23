
public class ToolboxSolution {

//    public static boolean isEven(int n) {
//        if (n > 0) return isOdd(n - 1);
//        else if (n < 0) return isOdd(n + 1);
//        else return true;
//    }
//
//    public static boolean isOdd(int n) {
//        if (n > 0) return isEven(n - 1);
//        else if (n < 0) return isEven(n + 1);
//        else return false;
//    }
//
//    a sligthly faster version is given below:
//
    public static boolean isOdd(int n) {
        if (n == 1 || n == -1)
            return true;
        else if (n == 0)
            return false;
        else if (n > 1)
            return isOdd(n - 2);
        else
            return isOdd(n + 2);
    }

    public static int evenSum(int n) {
        if (isOdd(n)) {
            n = n < 0 ? n + 1 : n - 1; // n is even, after this statement
        }

        return n < 0 ? -evenSumHelper(-n, 0) : evenSumHelper(n, 0);
    }

    private static int evenSumHelper(int n, int acc) {
        if (n == 0) return acc;
        else return evenSumHelper(n - 2, acc + n);
    }

    public static int multiplication(int x, int y) {
        if (x == 0 || y == 0)
            return 0;

        int n = multiplication(x < 0 ? -x : x, y, 0);

        return x < 0 ? -n : n;
    }

    private static int multiplication(int x, int y, int acc) {
        if (x == 0)
            return acc;

        return multiplication(x - 1, y, acc + y);
    }

    public static void reverse(int[] m) {
        if (m.length > 0)
            reverse(m, 0);
    }

    private static void reverse(int[] m, int i) {
        if (i >= m.length / 2) return;

        int tmp = m[i];
        m[i] = m[m.length - 1 - i];
        m[m.length - 1 - i] = tmp;

        reverse(m, i + 1);
    }

    public static int numberOfOddIntegers(int[] m) {
        return numberOfOddIntegers(m, 0, 0);
    }

    private static int numberOfOddIntegers(int[] m, int i, int j) {
        if (i == m.length) return j;

        if ((m[i] & 1) == 1)
            return numberOfOddIntegers(m, i + 1, j + 1);
        else
            return numberOfOddIntegers(m, i + 1, j);
    }

    public static int[] filterOdd(int[] m) {
        int[] n = new int[numberOfOddIntegers(m)];
        filterOdd(m, n, 0, 0);
        return n;
    }

    private static void filterOdd(int[] m, int[] n, int i, int j) {
        if (i == m.length) return;

        if ((m[i] & 1) == 1) {
            n[j] = m[i];
            filterOdd(m, n, i + 1, j + 1);
        } else filterOdd(m, n, i + 1, j);
    }
}
