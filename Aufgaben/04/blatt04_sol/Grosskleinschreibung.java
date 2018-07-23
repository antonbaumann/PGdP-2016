
public class Grosskleinschreibung extends MiniJava {

    public static void main(String[] args) {
        String input = readString();

        String output = "";
        int i = 0;

        int delta = 'a' - 'A';

        while (i < input.length()) {
            char c = input.charAt(i);

            if (c >= 'a' && c <= 'z') {
                output += (char) (c - delta);
            } else if (c >= 'A' && c <= 'Z') {
                output += (char) (c + delta);
            } else {
                output += c;
            }

            ++i;
        }

        write(output);
    }
}
