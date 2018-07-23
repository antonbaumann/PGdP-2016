
public class Caesar extends MiniJava {

    public static void main(String[] args) {
        String input = readString();
        int k = read();

        k = k % 26;
        if (k < 0) k = 26 + k;

        String output = "";
        int i = 0;

        while (i < input.length()) {
            char c = input.charAt(i);

            if (c >= 'a' && c <= 'z') {
                output += (char) ('a' + ((c - 'a') + k) % 26);
            } else if (c >= 'A' && c <= 'Z') {
                output += (char) ('A' + ((c - 'A') + k) % 26);
            } else {
                output += c;
            }

            ++i;
        }

        write(output);
    }
}
