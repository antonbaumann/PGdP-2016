/**
 * Die Klasse Position repraesentiert eine Spielsituation.
 *
 */
public class Position {

    // Player to move next
    private char next = 'W';

    // Animal placements, null-terminated
    private Animal[] myAnimals;
    private int nrAnimals;

    private int nrSwitched; // Anzahl Spielrunden

    // Konstruktor
    public Position() {
        myAnimals = new Animal[65];
        nrAnimals = 0;
        nrSwitched = 0;
    }


    // Registriert ein Tier auf einem bestimmten Feld.
    public void placeAnimal(Animal animal, String where) {
        for (int i = 0; i < myAnimals.length; i++) {
            if (null == myAnimals[i]) {
                myAnimals[i] = animal;
                animal.square = where;
                animal.position = this;
                nrAnimals++;
                return;
            } else if (animal == myAnimals[i]) {
                throw new RuntimeException("Animal " + animal + " already placed :(");
            }
        }
    }


    // Entfernt ein Tier von einem bestimmten Feld.
    public void removeAnimal(Animal animal) {
        for (int i = 0; i < myAnimals.length; i++) {
            if (animal == myAnimals[i]) {
                nrAnimals--;
                myAnimals[i]=myAnimals[nrAnimals];
                myAnimals[nrAnimals]=null;
                return;
            } else if (null == myAnimals[i]) {
                throw new RuntimeException("Animal " + animal + " not found :(");
            }
        }
    }

    // Bewegt ein Tier auf ein bestimmtes Feld.
    public void moveAnimal(Animal animal, String from, String to) {
        removeAnimal(animal);
        placeAnimal(animal, to);
    }

    public void clearPos() {
        myAnimals = new Animal[65];
        nrAnimals = 0;
        nrSwitched = 0;
    }

    public void reset(char movesNext) {
        clearPos();
        this.next = movesNext;
        placeAnimal(new Penguin(true), "a2");
        placeAnimal(new Penguin(true), "h2");
        placeAnimal(new Penguin(false), "a7");
        placeAnimal(new Penguin(false), "h7");
        placeAnimal(new Snake(true), "a1");
        placeAnimal(new Snake(true), "h1");
        placeAnimal(new Snake(false), "a8");
        placeAnimal(new Snake(false), "h8");
        placeAnimal(new Elephant(true), "b1");
        placeAnimal(new Elephant(true), "g1");
        placeAnimal(new Elephant(false), "b8");
        placeAnimal(new Elephant(false), "g8");
        placeAnimal(new Horse(true), "c1");
        placeAnimal(new Horse(true), "f1");
        placeAnimal(new Horse(false), "c8");
        placeAnimal(new Horse(false), "f8");
        placeAnimal(new Leopard(true), "e1");
        placeAnimal(new Leopard(true), "d1");
        placeAnimal(new Leopard(false), "d8");
        placeAnimal(new Leopard(false), "e8");
        String[] rs = {"b2","c2","d2","e2","f2","g2"};
        for (String s : rs) placeAnimal(new Rabbit(true), s);
        String[] rsm = {"b7","c7","d7","e7","f7","g7"};
        for (String s : rsm) placeAnimal(new Rabbit(false), s);
        nrSwitched = 0;
    }


    public Animal[] getAnimals() {
        //return java.util.Arrays.copyOfRange(myAnimals,0,nrAnimals);
        Animal[] tmp = new Animal[nrAnimals];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = myAnimals[i];
        }
        return tmp;
    }

    public Move[] possibleMoves(){
        int nrMoves = 0;
        Move[] allMoves = new Move[64*64];
        for (Animal a : this.getAnimals()) {
            if ((next == 'W') == a.female) {
                Move[] mv = a.possibleMoves();
                for (Move m : mv) {
                    allMoves[nrMoves++] = m;
                }
            }
        }
        //return java.util.Arrays.copyOfRange(allMoves,0,nrMoves).sort(...);
        Move[] tmp = new Move[nrMoves];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = allMoves[i];
        }
        // bubblesort
        for(int i = 0; i < tmp.length; i++){
            for(int j = tmp.length-1; j > i; j--){
                if (tmp[i].from.compareTo(tmp[j].from)>0
                 || (tmp[i].from.equals(tmp[j].from) && tmp[i].to.compareTo(tmp[j].to)>0)) {
                    Move temp = tmp[i];
                    tmp[i] = tmp[j];
                    tmp[j] = temp;
                }
            }
        }
        return tmp;
    }

    public boolean isLegalMove(Move move){
        for (Move m : possibleMoves()) {
            if (m.equals(move)) {
                return true;
            }
        }
        return false;
    }

    public void showMoves(){
        System.out.println("Mögliche Züge:");
        int newline = 0;
        for (Move m : possibleMoves()) {
            System.out.print(m + " ");
            newline++;
            if (newline % 12 == 0) System.out.println();
        }
        System.out.println();
    }

    public Animal getAnimalOnSquare(String square){
        for (int i = 0; i < myAnimals.length; i++) {
            if (null == myAnimals[i]) {
                break;
            } else if (myAnimals[i].square.equals(square)) {
                return myAnimals[i];
            }
        }
        return null;
    }

    public void switchSideToMove(){
        next = next == 'W' ? 'M' : 'W';
        nrSwitched++;
    }

    private void applyMove(Move move){
        Animal animal = getAnimalOnSquare(move.from);
        Animal prey = getAnimalOnSquare(move.to);
        if (prey != null) {
            removeAnimal(prey);
            ((Predator)animal).eatPrey();
        }
        moveAnimal(animal, move.from, move.to);
    }


    public void applyMoves(Move[] move){
        for (int i = 0; i < move.length; i++) {
            if (!isLegalMove(move[i])) {
                throw new RuntimeException("  >> Illegal move: " + move.toString());
            }
        }
        for (int i = 0; i < move.length; i++) {
            this.applyMove(move[i]);
        }
        this.switchSideToMove();
        if (nrSwitched % 2 == 0) {
            for (Animal a : this.getAnimals()) {
                a.sunset();
            }
        }
    }

    public boolean gameOver() {
        char winner = theWinner();
        return winner == 'W' || winner == 'M' || winner == 'N';
    }

    public char theWinner() {
        Animal[] remainingAnimals = getAnimals();
        if (remainingAnimals.length == 0){
            return 'N';
        }
        int nrPredators = 0, nrM = 0, nrW = 0;
        for (Animal a : remainingAnimals) {
            if (a instanceof Predator) nrPredators++;
            if (a.female) {
                nrW++;
            } else {
                nrM++;
            }
        }
        if (0 == nrPredators) {
            return nrM==nrW ? 'N' : (nrM<nrW ? 'W' : 'M');
        }
        return 'X';
    }




    // Ausgabe der Spielposition

    private static final int[] I = {8,7,6,5,4,3,2,1};
    private static final String[] J = {"a","b","c","d","e","f","g","h"};
    private static int toIndex(String s){return (s.charAt(0)-'a');}

    public Animal[][] boardRepresentation(){
        Animal[][] a = new Animal[8][8];
        for (int i : I) {
            for (String j : J) {
                for (int k = 0; k < myAnimals.length; k++) {
                    if (null == myAnimals[k]) {break;}
                    if (myAnimals[k].square.equals(j+i)) {
                        a[toIndex(j)][i-1] = myAnimals[k];
                    }
                }
            }
        }
        return a;
    }

    @Override
    public String toString(){
        String str = "   a b c d e f g h\n";
        Animal[][] ani = boardRepresentation();
        for (int i : I) {
            str += (i+" ");
            for (String j : J) {
                if (null == ani[toIndex(j)][i-1]) {
                    str += (i+toIndex(j))%2==1 ? Globals.ts_empty_square_dark : Globals.ts_empty_square_light;
                } else {
                    str += ani[toIndex(j)][i-1].toString();
                }
            }
            str += " " + i + "\n";
        }
        str += "  a b c d e f g h\nIt is " + next + "'s turn.\n";
        return str;
    }

}
