
public class Verhext extends MiniJava {

    // x^y
    public static int pow(int x, int y) {
        return java.math.BigInteger.valueOf(x).pow(y).intValueExact();
    }

    public static void main(String[] args) {
        String input = readString();
        int output;

        /* input your solution here */

        write(output);
    }
}

