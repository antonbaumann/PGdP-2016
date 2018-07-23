
public class Verhext extends MiniJava {

    // x^y
    public static int pow(int x, int y) {
        return java.math.BigInteger.valueOf(x).pow(y).intValueExact();
    }

    public static void main(String[] args) {
        String input = readString();
        int output;

        /* check if prefix is of the following forms:
             0x...
             0X...
            -0x...
            -0X...
         */
        int shift = input.charAt(0) == '-' ? 1 : 0;

        // Bonus-Aufgabe
        if (!(input.charAt(shift) == '0' && (input.charAt(shift + 1) == 'x' || input.charAt(shift + 1) == 'X'))) {
            write("Input string \"" + input + "\" is not a hexadecimal number.");
            return;
        }
        /* Bonus-Aufgabe: die obige Lösung erkennt nicht alle fehlerhaften Eingaben;
           der "versierte" Informatiker würde wohl eher mit einem regulären Ausdruck arbeiten;
           schade nur, dass die Methode matches(String regex) der Klasse String nicht erlaubt war ;-)
         */
        if (!input.matches("^-?0[xX][0-9a-zA-Z]([0-9a-zA-Z_]*[0-9a-zA-Z])?$")) {
            write("Input string \"" + input + "\" is not a hexadecimal number.");
            return;
        }

        int i = input.length() - 1;
        int j = input.charAt(0) == '-' ? 2 : 1;
        output = 0;
        int m = 1;
        while (i > j) {
            char c = input.charAt(i);
            int k;

            if (c >= 'a' && c <= 'f') {
                k = 10 + c - 'a';
            } else if (c >= 'A' && c <= 'F') {
                k = 10 + c - 'A';
            } else if (c >= '0' && c <= '9') {
                k = c - '0';
            } else if (c == '_') {
                --i;
                continue;
            } else {
                // Bonus-Aufgabe
                write("Input string \"" + input + "\" is not a hexadecimal number.");
                return;
            }

            output = output + m * k;
            m *= 16;
            --i;
        }

        if (input.charAt(0) == '-') {
            output *= -1;
        }

        write(output);
    }
}
