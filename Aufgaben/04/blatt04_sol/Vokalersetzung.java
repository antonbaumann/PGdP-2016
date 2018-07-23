
public class Vokalersetzung extends MiniJava {

    public static void main(String[] args) {

        boolean korrekt = false;
        char inG = 'A';
        char inK = 'a';
        while (!korrekt) {
            String eingabe = readString("Bitte Vokal eingeben");
            if (eingabe.length() != 1) {
                write("Geben Sie EINEN Vokal ein!");
            } else {
                char in = eingabe.charAt(0);
                if (in != 'A' && in != 'E' && in != 'I' && in != 'O' && in != 'U'
                        && in != 'a' && in != 'e' && in != 'i' && in != 'o' && in != 'u') {
                    write("Eingabe ist kein Vokal!");
                } else {
                    if (in > 'U') {
                        inG = (char) (in - ('a' - 'A'));
                        inK = in;
                    } else {
                        inG = in;
                        inK = (char) (in + ('a' - 'A'));
                    }
                    korrekt = true;
                }
            }
        }

        String text = "Hat der alte Hexenmeister\n"
                + "sich doch einmal wegbegeben!\n"
                + "Und nun sollen seine Geister\n"
                + "auch nach meinem Willen leben.\n"
                + "Seine Wort und Werke\n"
                + "merkt ich und den Brauch,\n"
                + "und mit Geistesstärke\n"
                + "tu ich Wunder auch.\n"
                + "Walle! walle\n"
                + "Manche Strecke,\n"
                + "daß, zum Zwecke,\n"
                + "Wasser fließe\n"
                + "und mit reichem, vollem Schwalle\n"
                + "zu dem Bade sich ergieße.";

        String out = "";

        int i = 0;
        while (i < text.length()) {
            if (text.charAt(i) == 'A' || text.charAt(i) == 'E' || text.charAt(i) == 'I'
                    || text.charAt(i) == 'O' || text.charAt(i) == 'U')
                out = out + inG;
            else {
                if (text.charAt(i) == 'a' || text.charAt(i) == 'e' || text.charAt(i) == 'i'
                        || text.charAt(i) == 'o' || text.charAt(i) == 'u')
                    out = out + inK;
                else
                    out = out + text.charAt(i);
            }
            i++;
        }
        write(out);
    }
}

