
public class FunctionalCaesar extends MiniJava {

    public static char shift(char c, int k) {
        // TODO
    }

    public static String encrypt(String s, int k) {
        // TODO
    }

    public static String decrypt(String s, int k) {
        return encrypt(s, -(k % 26));
    }

    public static void main(String[] args) {
        String input = readString();
        int k = read();
        String out = encrypt(input, k);
        write(out);
    }

}

