/**
 * Die Klasse Main enthaelt das Hauptprogramm.
 *
 * Im Hauptprogramm wird zuerst der Benutzer gefragt,
 * wer das Spiel beginnen soll.
 *
 * Dann wird das Spiel gestartet.
 *
 */
public class Main {

    public static void main(String args[]) {
        Game game = new Game();
        game.startGame(IO.readInt("Wer beginnt? (1 = W, 0 = M) ",0,1) == 1);
    }

}
