/**
 * Die Klasse Move repraesentiert einen einzelnen Zug.
 *
 * Es gibt zwei Konstruktoren. Einer bekommt
 * Ausgangsfeld und Zielfeld uebergeben, der andere
 * bekommt nur den eingegebenen Zug in der Form
 * <Ausgangsfeld><Zielfeld> als String uebergeben,
 * also z. B. "a7c5" fuer den Zug von "a7" nach "c5".
 */
public class Move {

    public final String from;
    public final String to;

    public Move(String from, String to){
        this.from = from;
        this.to = to;
    }

    public Move(String move){
        this.from = "" + move.charAt(0)+move.charAt(1);
        this.to = "" + move.charAt(2)+move.charAt(3);
    }

    public static String step(String from, int xOffset, int yOffset) {
        char x = (char) (from.charAt(0) + xOffset);
        char y = (char) (from.charAt(1) + yOffset);
        if (x < 'a' || x > 'h' || y < '1' || y > '8') {
            return null;
        }
        return x + "" + y;
    }

    @Override
    public String toString(){
        return from + to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false; // instanceof is OK, too
        final Move other = (Move) o;
        return this.from.equals(other.from) && this.to.equals(other.to);
    }

}
