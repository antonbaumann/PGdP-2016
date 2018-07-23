/**
 * Die Klasse Game fuehrt die Benutzerinteraktion durch.
 *
 */

public class Game {

    private Position pos;


    private void showOptions(){
        System.out.println("Geben Sie einen Zug (z. B. e2e4) oder einen Befehl ein!"
         + "\n q : Spiel beenden \n a : Eingegebene Züge ausführen\n"
         + " w : Eingabe korrigieren (nochmal ganz von vorne beginnen)");
    }

    private boolean occursFrom(String square, Move[] moves, int nrMoves) {
        for (int i = 0; i < nrMoves; i++) {
            if (square.equals(moves[i].from)) return true;
        }
        return false;
    }

    private boolean occursTo(String square, Move[] moves, int nrMoves) {
        for (int i = 0; i < nrMoves; i++) {
            if (square.equals(moves[i].to)) return true;
        }
        return false;
    }

    /**
     * Startet ein neues Spiel. Der Benutzer wird ueber das Spielgeschehen
     * informiert. Dazu gehoert auch die Information, wie lange die
     * einzelnen Raubtiere noch ohne Essen auskommen koennen.
     *
     */
    public void startGame(boolean ladiesFirst){
        pos = new Position();
        pos.reset(ladiesFirst ? 'W' : 'M');
        while (true) {
            //System.out.println(pos);
            pos.showMoves();
            showOptions();
            Move move = null;
            Move[] moves = new Move[4];
            int nrMoves = 0;
            boolean predatorMove = false;
            askForMoves : while (true) {
                System.out.println(pos);
                String input = IO.readString("Eingabe: ");
                if (input.length() == 4) {
                    move = new Move(input);
                }
                if (input.length() == 4 && pos.isLegalMove(move)) {
                    if (occursFrom(move.from, moves, nrMoves)) {
                        System.out.println("Animal already registered for a different move. Ignoring input.");
                        continue;
                    } else if (occursTo(move.to, moves, nrMoves)) {
                        System.out.println("Target square already used for previously entered move. Ignoring input.");
                        continue;
                    } else if (pos.getAnimalOnSquare(move.from) instanceof Predator) {
                        if (predatorMove) {
                            System.out.println("Only one predator may move. Ignoring input.");
                            continue;
                        }
                        predatorMove = true;
                    } else if (nrMoves == 3 && !predatorMove) {
                        System.out.println("Only three vegetarians may move. Ignoring input.");
                        continue;
                    }
                    moves[nrMoves++] = move;
                    if (nrMoves == 4) break askForMoves;

                } else if (input.equals("a")) {
                    break askForMoves;
                } else if (input.equals("q")) {
                    return;
                } else if (input.equals("w")) {
                    System.out.println("All entered moves are discarded.");
                    nrMoves = 0;
                    predatorMove = false;
                    continue;
                } else {
                    System.out.println(" Ich habe Sie nicht verstanden.");
                    System.out.println(" Sollten Sie einen Zug eingegeben"
                       + " haben, so halte ich ihn für nicht regelgemäß."
                       + " Sie können das Spiel durch Eingabe von q beenden.");
                }
            }
            System.out.println("\nApplying move(s)...\n");
            //Move tmp=java.util.Arrays.copyOfRange(moves,0,nrMoves);
            Move[] tmp = new Move[nrMoves];
            for (int i = 0; i < tmp.length; i++) {
                tmp[i] = moves[i];
            }

            pos.applyMoves(tmp);

            if (pos.gameOver()) {
                char winner = pos.theWinner();
                if (winner == 'W' || winner == 'M')  {
                    System.out.println("\n" + winner + " gewinnt das Spiel.");
                } else {
                    System.out.println("\nDas Spiel endet mit einem Unentschieden.");
                }
                return;
            } else {
                for (Animal a : pos.getAnimals()) {
                    if (a instanceof Predator) {
                        Predator p = (Predator) a;
                        System.out.println(" " + p.square + ":" + p
                            + " kann weitere " + p.daysBeforeDeath()
                            + " Tage ohne Beute auskommen."); //bzw. Spielrunden
                    }
                }
                System.out.println();
            }
        }
    }
}
