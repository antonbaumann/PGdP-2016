import java.util.Scanner; // Einlesen ueber die Konsole

public class Raten extends MiniJava {

    private int nrPlayers; // Anzahl Spieler


    /**
     * Im Konstruktor wird die Anzahl Spieler uebergeben.
     *
     * @param nrPlayers Anzahl Spieler
     */
    public Raten(int nrPlayers){
        this.nrPlayers = nrPlayers;
        if (nrPlayers <= 0) {
            // should throw an exception instead
            System.err.println("\nException in Raten constructor! Not enough players.\n");
            nrPlayers = 2;
        }
    }


    /**
     * Startet das Spiel.
     */
    public void startGame(){
        // Zufallszahl zwischen 10 und 100 (jeweils inklusive)
        int max = generateNumber(10, 100);

        int previous = 9; // erste Zahl >= 10
        while (true) {
            for (int player = 1; player <= nrPlayers; player++) {
                int guessedNumber = previous;
                while (guessedNumber <= previous) {
                    guessedNumber = readIntRange(player + ". Spieler: welche Zahl (min. " + (previous + 1) + ")? ", previous + 1, Integer.MAX_VALUE);
                    if (guessedNumber > max) {
                        System.out.println("Schade! " + guessedNumber + " > " + max + "\nLeider verloren.");
                        return;                    
                    }
                }
                previous = guessedNumber;
            }
        }
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
