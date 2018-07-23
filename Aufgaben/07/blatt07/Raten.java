import java.util.Scanner; // Einlesen ueber die Konsole

public class Raten extends MiniJava {

    //TODO Attribute?


    /**
     * Im Konstruktor wird die Anzahl Spieler uebergeben.
     *
     * @param nrPlayers Anzahl Spieler
     */
    public Raten(int nrPlayers){
        // TODO Anzahl Spieler merken
    }


    /**
     * Startet das Spiel.
     */
    public void startGame(){
        //TODO
    }


    /**
     * Liest eine Zahl aus einem bestimmten Bereich von der Konsole ein.
     * Zuvor wird der uebergebene String auf der Konsole ausgegeben.
     * Die Aktion (Ausgabe und Einlesen) wird so lange wiederholt,
     * bis eine Zahl aus dem spezifizierten Bereich eingegeben wurde.
     *
     * @param msg wird jedes Mal vor dem Einlesen ausgegeben
     * @param lower kleinste akzeptierte Zahl
     * @param upper groesste akzeptierte Zahl
     *
     * @return eingelesene Zahl
     */
    public static int readIntRange(String msg, int lower, int upper){
        int result;
        do {
            System.out.print(msg);
            result = (new Scanner (System.in)).nextInt();
        } while (result < lower || result > upper);
        return result;
    }


    /**
     * Liefert eine zufaellige Zahl aus einem bestimmten Bereich.
     *
     * @param lower kleinste moegliche Zahl
     * @param upper groesste moegliche Zahl
     *
     * @return die Zahl!
     */
    public static int generateNumber(int lower, int upper){
        return lower + (new java.util.Random()).nextInt(upper-lower+1);
    }


    public static void main(String[] args) {
        Raten guess = new Raten(3);
        guess.startGame();
    }

}
