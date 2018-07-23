
public class Tailrec1 {

    // x^y
    public static int pow(int x, int y) {
        return java.math.BigInteger.valueOf(x).pow(y).intValueExact();
    }

    // function f recursive
    public static int frec(int x) {
        return grec(x, 0);
    }

    // helper function g recursive
    public static int grec(int x, int pos) {
        if (x / 10 == 0) {
            return pow(x, pos);
        }
        return pow(x % 10, pos) + grec(x / 10, ++pos);
    }

    // function f tail recursive
    public static int ftailrec(int x) {
        return gtailrec(x, 0, 0);
    }

    // helper function g tail recursive
    public static int gtailrec(int x, int pos, int calc) {
        if (x / 10 == 0)
            return calc + pow(x, pos);
        int nextpos = pos + 1;
        return gtailrec(x / 10, nextpos, calc + pow(x % 10, pos));
    }

    public static void main(String[] args) {
        int n = 12345;

        System.out.println("f recursive: " + frec(n));
        System.out.println("f tailrec: " + ftailrec(n));
    }

}
