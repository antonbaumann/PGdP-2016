
public class FunctionalCaesarSolution extends MiniJava {

    public static char shift(char c, int k) {
        char base;

        k = k % 26;
        if (k < 0) k = 26 + k;

        if (c >= 'a' && c <= 'z') {
            base = 'a';
        } else if (c >= 'A' && c <= 'Z') {
            base = 'A';
        } else {
            return c;
        }

        return (char) (base + ((c - base) + k) % 26);
    }

    public static String encrypt(String s, int k) {
        String output = "";

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            output += shift(c, k);
        }

        return output;
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
