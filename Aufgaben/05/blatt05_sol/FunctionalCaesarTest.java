
import java.util.Random;

public class FunctionalCaesarTest {

    private static int points;

    public static char randomChar() {
        switch (new Random().nextInt(4)) {
            case 0: // capital letter
                return (char) (97 + new Random().nextInt(26));
            case 1: // lower case letter
                return (char) (65 + new Random().nextInt(26));
            case 2: // digit
                return (char) (48 + new Random().nextInt(10));
            case 3: // special char
                return (char) (33 + new Random().nextInt(15));
            default:
                throw new IllegalArgumentException("π is exactly three");
        }
    }

    public static String randomString() {
        StringBuilder out = new StringBuilder();

        int n = new Random().nextInt(20) + 1;

        for (int i = 0; i < n; ++i) {
            int m = new Random().nextInt(10) + 2;

            for (int j = 0; j < m; ++j) {
                out.append(randomChar());
            }

            out.append(" ");
        }

        return out.deleteCharAt(out.length() - 1).toString();
    }

    public static void testShift(boolean positive) {
        for (int i = 0; i < 10000; ++i) {
            char c = randomChar();
            int k = positive ? new Random().nextInt() : new Random().nextInt() * -1;

            char x = FunctionalCaesarSolution.shift(c, k);
            char y = FunctionalCaesar.shift(c, k);

            if (x != y) {
                System.out.println("testShift failed for (" + c + "," + k + ") => " + y + " should be " + x);
                return;
            }

            char cprime = FunctionalCaesar.shift(y, -k);

            if (c != cprime) {
                System.out.println("testShift failed for (" + y + "," + -k + ") => " + cprime + " should be " + c);
                return;
            }
        }

        ++points;
    }

    public static void testEncrypt() {
        for (int i = 0; i < 10000; ++i) {
            String random = randomString();
            int k = new Random().nextInt();

            String ciphertext_sol = FunctionalCaesarSolution.encrypt(random, k);
            String ciphertext = FunctionalCaesar.encrypt(random, k);

            if (!ciphertext_sol.equals(ciphertext)) {
                System.out.println("testEncrypt failed for (" + random + "," + k + ") => " + ciphertext + " should be " + ciphertext_sol);
                return;
            }

            String cleartext = FunctionalCaesar.decrypt(ciphertext, k);

            if (!random.equals(cleartext)) {
                System.out.println("testDecrypt failed for (" + ciphertext + "," + k + ") => " + cleartext + " should be " + random);
                return;
            }
        }

        ++points;
    }

    public static void main(String[] args) {
        testShift(true);
        testShift(false);
        testEncrypt();

        System.out.println("You get " + points + " points.");
    }
}
