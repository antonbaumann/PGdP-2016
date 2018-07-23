import java.util.Scanner;

public class DameSpiel extends MiniJava {

    private static final int minNrRows = 5;
    private static final int maxNrRows = 8;

    private int nrRows, nrColumns; // Board dimensions
    private boolean[][] board;     // true = queen, false = empty
    private boolean whiteToMove;   // Whose turn it is
    private String white, black;   // Players' names


    /**
     * Registriert Spielernamen fuer Weiss und Schwarz.
     *
     * @param white Name des als 'Weiss' bezeichneten Spielers
     * @param black Name des als 'Schwarz' bezeichneten Spielers
     */
    public DameSpiel(String white, String black){
        this.white = white;
        this.black = black;
    }


    /**
     * Gibt das Spielbrett aus.
     */
    private void printBoard(){
        for (int j = board[0].length - 1; j >= 0; j--) {
            System.out.print("\n " + (1 + j));
            for (int i = 0; i < board.length; i++) {
                System.out.print(board[i][j] ? " X" : " -");
            }
        }
        System.out.print("\n  ");
        for (int i = 1; i <= board.length; i++) {
            System.out.print(" " + i);
        }
        System.out.println("\n" + (whiteToMove ? white : black) + " ist am Zug.");
    }


    /**
     * Initialisiert das Spielbrett ueberall mit false.
     * Dazu wird (ggf. neuer) Speicher allokiert.
     */
    private void initBoard(){
        // Initialize board with default values (false)
        this.board = new boolean[this.nrRows][this.nrColumns];
    }


    /**
     * Ermittelt die Groesse des Spielbretts gemaess den Spielregeln.
     * Das Ergebnis der Abfrage wird in den Attributen nrRows und nrColumns abgelegt.
     */
    private void determineBoardSize(){
        nrRows = readInt(white + ": Bitte Breite a des Spielbretts eingeben!\n("
                + minNrRows + " <= a <= " + maxNrRows + ") ", minNrRows, maxNrRows);
        nrColumns = readInt(black + ": Bitte Länge b des Spielbretts eingeben!\n("
                + (nrRows - 1) + " <= b <= " + (nrRows + 1) + ") ", nrRows - 1, nrRows + 1);
    }


    /**
     * Ermittelt, wer anfaengt zu ziehen.
     * Das Ergebnis der Abfrage wird im Attribut whiteToMove abgelegt.
     */
    private void determineFirstPlayer(){
        whiteToMove = readInt(white + ": Wollen Sie anfangen? (1 = Ja, 0 = Nein) ", 0, 1) == 1;
    }


    /**
     * Liest einen Zug oder -1 ein (-1 fuer Abbruch).
     *
     * @return eingelesene Zahl
     */
    private int askMove(){
        String msg = (whiteToMove ? white : black) + ": Geben Sie einen Zug ein!\n"
                   + "Zehnerstelle = Breite, Einerstelle = Länge. Abbruch mit -1 ";
        int choice = readInt(msg, -1, 100);
        return choice;
    }


    /**
     * Prueft, ob die uebergebenen Koordinaten innerhalb des Spielbretts liegen.
     *
     * @param x X-Koordinate (1 <= x <= Breite)
     * @param y Y-Koordinate (1 <= y <= Laenge)
     */
    private boolean validCoordinates(int x, int y){
        if (x >= board.length || x < 0) return false;
        if (y >= board[0].length || y < 0) return false;
        return true;
    }


    /**
     * Prueft, ob in dieser Richtung eine Spielfigur im Weg steht.
     * Es werden die zu pruefende Position (a,b) sowie ein 2D-Offset (dx,dy)
     * uebergeben, welcher einen einzelnen Schritt in die Richtung definiert.
     *
     * @param a X-Koordinate (1 <= a <= Breite)
     * @param b Y-Koordinate (1 <= b <= Laenge)
     * @param dx Offset in X-Richtung
     * @param dy Offset in Y-Richtung
     * @return true bedeutet, dass eine Spielfigur im Weg ist.
     */
    private boolean occupied(int a, int b, int dx, int dy){
        int i = 0;
        while (true) {
            i++;
            if (!validCoordinates(a+i*dx, b+i*dy)) {
                break;
            }
            if (board[a+i*dx][b+i*dy]) return true;
        }
        return false;
    }


    /**
     * Prueft, ob ein legaler Zug uebergeben wird.
     * Ein Zug ist legal, wenn
     * (1.) er gueltige Koordinaten beinhaltet (innerhalb der Begrenzungen des Bretts)
     * und
     * (2.) das Spielbrett an den entsprechenden Koordinaten frei (false) ist
     * und 
     * (3.) von dort aus kein anderes Feld in gerader oder diagonaler Richtung erreicht
     *      werden kann, das nicht frei ist.
     *
     * @param move der zu pruefende Zug
     */
    private boolean isLegalMove(int move){
        int a = move / 10 - 1, b = move % 10 - 1; // should use function instead
        if (!validCoordinates(a, b)) {
            return false;
        }
        if (board[a][b]) return false;
        int[][] direction = {{1,1}, {1,0}, {0,1}, {-1,-1}, {-1,0}, {0,-1}, {-1,1}, {1,-1}};
        for (int i = 0; i < direction.length; i++) {
            if (occupied(a, b, direction[i][0], direction[i][1])) {
                return false;
            }
        }
        return true;
    }


    /**
     * Prueft, ob noch Zuege moeglich sind.
     * Falls ja, wird false zurueckgegeben, sonst true.
     *
     */
    private boolean gameOver(){
        for (int move = 11; move <= 99; move++) {
            if (isLegalMove(move)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Fuehrt den Zug aus.
     *
     * @param move der auszufuehrende Zug!
     */
    private void applyMove(int move){
        board[move / 10 - 1][move % 10 - 1] = true;
        whiteToMove = !whiteToMove;
    }


    /**
     * Startet die Hauptschleife des Spiels
     * mit der Abfrage nach Zuegen.
     * Die Schleife wird durch Eingabe von -1 beendet.
     */
    private void mainLoop(){
        int move;
        while (true) {
            do {
                move = askMove();
                if (move == -1) {
                    System.out.println("Beendet.");
                    return;
                }
            } while (!isLegalMove(move));
            applyMove(move);
            printBoard();
            if (gameOver()) {
                return;
            }
        }
    }


    /**
     * Informiert die Benutzerin ueber den Ausgang des Spiels.
     * Speziell: Wer hat gewonnen (Weiss oder Schwarz)?
     */
    private void reportWinner(){
        System.out.println((whiteToMove ? black : white) + " hat gewonnen.");
    }


    /**
     * Startet das Spiel.
     */
    public void startGame(){
        determineBoardSize();
        initBoard();
        determineFirstPlayer();
        printBoard();
        mainLoop();
        reportWinner();
    }


    // Zahl aus bestimmtem Bereich einlesen
    public static int readInt(String msg, int lower, int upper){
        // Mit MiniJava:
        // int result;
        // do {
        //     result = readInt(msg);
        // } while (result < lower || result > upper);
        // return result;

        // Ueber die Konsole:
        int result;
        do {
            System.out.print(msg);
            result = (new Scanner (System.in)).nextInt();
        } while (result < lower || result > upper);
        return result;
    }


    public static void main(String[] args) {
        DameSpiel ds = new DameSpiel("Weiß", "Schwarz");
        ds.startGame();
    }

}
