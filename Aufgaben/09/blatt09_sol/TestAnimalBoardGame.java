/**
 * Nutzt reset(char movesNext), applyMoves(Move[] move), theWinner() und toString()
 * aus der Klasse Position, um auf korrekte Funktionalitaet zu testen.
 *
 * ==> Zu testende Klassen in selbes Verzeichnis legen
 * und ggf. Package-Deklarationen entfernen.
 * Beispiel-Script s.u. (vorher Backup machen!)
 *
 * #!/bin/bash
 *
 * for filename in *.java; do
 *   echo trying to remove Java package declaration from file "$filename"...
 *   sed -i 's/package .*;//' "$filename"
 * done
 */
 
/**
 *    Erweitertes Testen inklusive AnimalXY.possibleMoves().
 *
 * 1. In der Klasse Position bei  Animal[] myAnimals  und  char next
 *    die Sichtbarkeit auf public setzen
 * 2. Hier im Testprogramm nach den BEIDEN mit //TODO gekennzeichneten
 *    Zeilen JEWEILS (!) die Kommentierung entfernen
 *
 */




import java.util.Scanner;
import java.util.Arrays;

class AnimalSol {

    public boolean female;
    public boolean alive;
    public String square;
    public PositionSol position;

    public AnimalSol(boolean female) {
        this.female = female;
        this.alive = true;
    }


    public MoveSol[] possibleMoves(){
        return null;
    }


    public void sunset(){
        throw new RuntimeException("Method sunset should have been overridden");
    }

}

class VegetarianSol extends AnimalSol {

    public VegetarianSol(boolean female) {
        super(female);
    }

    @Override
    public void sunset(){}

}
class PredatorSol extends AnimalSol {

    public int eat, left;

    public static final int sVal = 3;

    public PredatorSol(int start, int eat, boolean female){
        super(female);
        this.eat = eat;
        this.left = start;
    }

    public int daysBeforeDeath() {
        return left;
    }

    @Override
    public void sunset(){
        if(left > 0){
            left--;
        } else if (this.alive && this.left == 0) {
            this.alive = false;
            if (this.position != null) {
                this.position.removeAnimal(this);
            }
        }
    }

    public void eatPrey(){
        this.left = eat;
    }

}

class SnakeSol extends PredatorSol {

    static int start = sVal;
    static int eat = sVal;

    public SnakeSol(boolean female) {
        super(start, eat, female); // Schlange kann 9 Tage bzw. Spielrunden ohne Essen auskommen.
    }

    public MoveSol[] possibleMoves() {
        int last = -1;
        String to;
        MoveSol[] myMoves = new MoveSol[63];

        int[] x = {1, 1, -1,1,  1,-1, -1,-1};
        int[] y = {1,-1,  1,1, -1,-1, -1, 1};
        int[] z = {0,2,4,6};
        for (int i : z) {
            to = MoveSol.step(this.square,x[i],y[i]);
            while (null != to) {
                boolean moveOK = false;
                AnimalSol other = this.position.getAnimalOnSquare(to);
                if (other == null) {
                    moveOK = true;
                } else if (other instanceof VegetarianSol && this.female != other.female) {
                    moveOK = true;
                }
                if (moveOK) {
                    myMoves[++last] = new MoveSol(this.square, to);
                    i++; if (i % 2 == 0) i-=2;
                    to = MoveSol.step(to,x[i],y[i]);
                }
                if (!moveOK || other != null) {
                    break;
                }
            }
        }

        //return java.util.Arrays.copyOfRange(myMoves,0,last+1);
        MoveSol[] tmp = myMoves;
        myMoves = new MoveSol[last+1];
        for (int i = 0; i < myMoves.length; i++) {
            myMoves[i] = tmp[i];
        }
        return myMoves;
    }


    @Override
    public String toString(){
        return this.female
          ? (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_female_snake_dark : GlobalsSol.ts_female_snake_light)
          : (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_male_snake_dark : GlobalsSol.ts_male_snake_light);
    }

}

class PenguinSol extends PredatorSol {

    static int start = sVal;
    static int eat = sVal;

    public PenguinSol(boolean female) {
        super(start, eat, female); // Pinguin kann 12 Tage bzw. Spielrunden ohne Essen auskommen.
    }


    public MoveSol[] possibleMoves() {
        int last = -1;
        String to;
        MoveSol[] myMoves = new MoveSol[63];

        int[] x = {-1,0,1,1,1,0,-1,-1};
        int[] y = {1,1,1,0,-1,-1,-1,0};
        for (int i = 0; i < x.length; i++) {
            to = MoveSol.step(this.square,x[i],y[i]);
            if (null != to) {
                boolean moveOK = false;
                if (this.position.getAnimalOnSquare(to) == null) {
                    moveOK = true;
                } else {
                    AnimalSol other = this.position.getAnimalOnSquare(to);
                    if (other instanceof VegetarianSol && this.female != other.female) {
                        moveOK = true;
                    }
                }
                if (moveOK) myMoves[++last] = new MoveSol(this.square, to);
            }
        }

        //return java.util.Arrays.copyOfRange(myMoves,0,last+1);
        MoveSol[] tmp = myMoves;
        myMoves = new MoveSol[last+1];
        for (int i = 0; i < myMoves.length; i++) {
            myMoves[i] = tmp[i];
        }
        return myMoves;
    }


    @Override
    public String toString(){
        return this.female
          ? (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_female_penguin_dark : GlobalsSol.ts_female_penguin_light)
          : (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_male_penguin_dark : GlobalsSol.ts_male_penguin_light);
    }

}

class LeopardSol extends PredatorSol {

    static int start = sVal;
    static int eat = sVal;

    public LeopardSol(boolean female) {
        super(start, eat, female); // Leopard kann 5 Tage bzw. Spielrunden ohne Essen auskommen.
    }

    public MoveSol[] possibleMoves() {
        int last = -1;
        String to;
        MoveSol[] myMoves = new MoveSol[63];

        int[] x = {-1,0,1,1,1,0,-1,-1};
        int[] y = {1,1,1,0,-1,-1,-1,0};
        for (int i = 0; i < x.length; i++) {
            to = MoveSol.step(this.square,x[i],y[i]);
            while (null != to) {
                boolean moveOK = false;
                AnimalSol other = this.position.getAnimalOnSquare(to);
                if (other == null) {
                    moveOK = true;
                } else if (other instanceof VegetarianSol && this.female != other.female) {
                    moveOK = true;
                }
                if (moveOK) {
                    myMoves[++last] = new MoveSol(this.square, to);
                    to = MoveSol.step(to,x[i],y[i]);
                }
                if (!moveOK || other != null) {
                    break;
                }
            }
        }

        //return java.util.Arrays.copyOfRange(myMoves,0,last+1);
        MoveSol[] tmp = myMoves;
        myMoves = new MoveSol[last+1];
        for (int i = 0; i < myMoves.length; i++) {
            myMoves[i] = tmp[i];
        }
        return myMoves;
    }


    @Override
    public String toString(){
        return this.female
          ? (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_female_leopard_dark : GlobalsSol.ts_female_leopard_light)
          : (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_male_leopard_dark : GlobalsSol.ts_male_leopard_light);
    }

}

class HorseSol extends VegetarianSol {

    public HorseSol(boolean female) {
        super(female);
    }

    public MoveSol[] possibleMoves() {
        int last = -1;
        String to;
        MoveSol[] myMoves = new MoveSol[63];

        int[] x = {-1,0,1,1,1,0,-1,-1};
        int[] y = {1,1,1,0,-1,-1,-1,0};
        for (int i = 0; i < x.length; i++) {
            if ((x[i]+2+y[i])%2==0) {
                to = MoveSol.step(this.square,x[i],y[i]);
                if (null != to) to = MoveSol.step(to,x[i],y[i]);
                if (null != to) {
                    if (this.position.getAnimalOnSquare(to) == null) {
                        myMoves[++last] = new MoveSol(this.square, to);
                    }
                    to = MoveSol.step(to,x[i],y[i]);
                }
                if (null != to && this.position.getAnimalOnSquare(to) == null) {
                    myMoves[++last] = new MoveSol(this.square, to);
                }
            } else {
                to = MoveSol.step(this.square,x[i],y[i]);
                if (null != to) {
                    if (this.position.getAnimalOnSquare(to) == null) {
                        myMoves[++last] = new MoveSol(this.square, to);
                    }
                }
            }
        }

        //return java.util.Arrays.copyOfRange(myMoves,0,last+1);
        MoveSol[] tmp = myMoves;
        myMoves = new MoveSol[last+1];
        for (int i = 0; i < myMoves.length; i++) {
            myMoves[i] = tmp[i];
        }
        return myMoves;
    }


    @Override
    public String toString(){
        return this.female
          ? (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_female_horse_dark : GlobalsSol.ts_female_horse_light)
          : (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_male_horse_dark : GlobalsSol.ts_male_horse_light);
    }

}

class RabbitSol extends VegetarianSol {

    public RabbitSol(boolean female) {
        super(female);
    }

    public MoveSol[] possibleMoves() {
        int last = -1;
        String to;
        MoveSol[] myMoves = new MoveSol[63];

        int[] x = {-1,0,1,1,1,0,-1,-1};
        int[] y = {1,1,1,0,-1,-1,-1,0};
        for (int i = 0; i < x.length; i++) {
            to = MoveSol.step(this.square,x[i],y[i]);
            if (null != to) {
                if (this.position.getAnimalOnSquare(to) == null) {
                    myMoves[++last] = new MoveSol(this.square, to);
                }
            }
        }

        //return java.util.Arrays.copyOfRange(myMoves,0,last+1);
        MoveSol[] tmp = myMoves;
        myMoves = new MoveSol[last+1];
        for (int i = 0; i < myMoves.length; i++) {
            myMoves[i] = tmp[i];
        }
        return myMoves;
    }


    @Override
    public String toString(){
        return this.female
          ? (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_female_rabbit_dark : GlobalsSol.ts_female_rabbit_light)
          : (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_male_rabbit_dark : GlobalsSol.ts_male_rabbit_light);
    }

}

class ElephantSol extends VegetarianSol {

    public ElephantSol(boolean female) {
        super(female);
    }


    public MoveSol[] possibleMoves() {
        int last = -1;
        String to;
        MoveSol[] myMoves = new MoveSol[63];

        int[] x = {0,1,0,-1};
        int[] y = {1,0,-1,0};
        for (int i = 0; i < x.length; i++) {
            to = MoveSol.step(this.square,x[i],y[i]);
            while (null != to) {
                AnimalSol other = this.position.getAnimalOnSquare(to);
                if (other == null) {
                    myMoves[++last] = new MoveSol(this.square, to);
                    to = MoveSol.step(to,x[i],y[i]);
                } else {
                    break;
                }
            }
        }

        //return java.util.Arrays.copyOfRange(myMoves,0,last+1);
        MoveSol[] tmp = myMoves;
        myMoves = new MoveSol[last+1];
        for (int i = 0; i < myMoves.length; i++) {
            myMoves[i] = tmp[i];
        }
        return myMoves;
    }


    @Override
    public String toString(){
        return this.female
          ? (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_female_elephant_dark : GlobalsSol.ts_female_elephant_light)
          : (GlobalsSol.darkSquare(this.square) ? GlobalsSol.ts_male_elephant_dark : GlobalsSol.ts_male_elephant_light);
    }

}


class GlobalsSol {

    public static final boolean ANSI = TestAnimalBoardGame.getANSI();

    private static final String ANSI_W_LIGHT = "\u001B[34;47m";
    private static final String ANSI_W_DARK  = "\u001B[34;43m";
    private static final String ANSI_M_LIGHT = "\u001B[31;47m";
    private static final String ANSI_M_DARK  = "\u001B[31;43m";

    public static final String ts_male_penguin_light = ANSI  ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F427)) + " " + "\u001B[0m") : " P";
    public static final String ts_male_horse_light = ANSI    ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F434)) + " " + "\u001B[0m") : " H";
    public static final String ts_male_elephant_light = ANSI ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F418)) + " " + "\u001B[0m") : " E";
    public static final String ts_male_snake_light = ANSI    ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F40D)) + " " + "\u001B[0m") : " S";
    public static final String ts_male_leopard_light = ANSI  ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F406)) + " " + "\u001B[0m") : " L";
    public static final String ts_male_rabbit_light = ANSI   ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F407)) + " " + "\u001B[0m") : " R";

    public static final String ts_male_penguin_dark = ANSI  ? (ANSI_M_DARK + new String(Character.toChars(0x1F427)) + " " + "\u001B[0m") : " P";
    public static final String ts_male_horse_dark = ANSI    ? (ANSI_M_DARK + new String(Character.toChars(0x1F434)) + " " + "\u001B[0m") : " H";
    public static final String ts_male_elephant_dark = ANSI ? (ANSI_M_DARK + new String(Character.toChars(0x1F418)) + " " + "\u001B[0m") : " E";
    public static final String ts_male_snake_dark = ANSI    ? (ANSI_M_DARK + new String(Character.toChars(0x1F40D)) + " " + "\u001B[0m") : " S";
    public static final String ts_male_leopard_dark = ANSI  ? (ANSI_M_DARK + new String(Character.toChars(0x1F406)) + " " + "\u001B[0m") : " L";
    public static final String ts_male_rabbit_dark = ANSI   ? (ANSI_M_DARK + new String(Character.toChars(0x1F407)) + " " + "\u001B[0m") : " R";


    public static final String ts_female_penguin_light = ANSI  ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F427)) + " " + "\u001B[0m") : " p";
    public static final String ts_female_horse_light = ANSI    ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F434)) + " " + "\u001B[0m") : " h";
    public static final String ts_female_elephant_light = ANSI ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F418)) + " " + "\u001B[0m") : " e";
    public static final String ts_female_snake_light = ANSI    ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F40D)) + " " + "\u001B[0m") : " s";
    public static final String ts_female_leopard_light = ANSI  ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F406)) + " " + "\u001B[0m") : " l";
    public static final String ts_female_rabbit_light = ANSI   ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F407)) + " " + "\u001B[0m") : " r";

    public static final String ts_female_penguin_dark = ANSI  ? (ANSI_W_DARK + new String(Character.toChars(0x1F427)) + " " + "\u001B[0m") : " p";
    public static final String ts_female_horse_dark = ANSI    ? (ANSI_W_DARK + new String(Character.toChars(0x1F434)) + " " + "\u001B[0m") : " h";
    public static final String ts_female_elephant_dark = ANSI ? (ANSI_W_DARK + new String(Character.toChars(0x1F418)) + " " + "\u001B[0m") : " e";
    public static final String ts_female_snake_dark = ANSI    ? (ANSI_W_DARK + new String(Character.toChars(0x1F40D)) + " " + "\u001B[0m") : " s";
    public static final String ts_female_leopard_dark = ANSI  ? (ANSI_W_DARK + new String(Character.toChars(0x1F406)) + " " + "\u001B[0m") : " l";
    public static final String ts_female_rabbit_dark = ANSI   ? (ANSI_W_DARK + new String(Character.toChars(0x1F407)) + " " + "\u001B[0m") : " r";


    public static final String ts_empty_square_light = ANSI ? "\u001B[47m" + "  " + "\u001B[0m" : "  ";
    public static final String ts_empty_square_dark  = ANSI ? "\u001B[43m" + "  " + "\u001B[0m" : "  ";

    public static boolean darkSquare(String square){
        if (null == square) return false;
        return (square.charAt(0)+square.charAt(1)) % 2 == 0;
    }

}

class IOSol {

    public static String readString(String msg) {
        System.out.print(msg);
        return (new Scanner (System.in)).nextLine();
    }

    public static int readInt(String msg, int low, int high) {
        int result;
        do {
            System.out.print(msg);
            result = (new Scanner (System.in)).nextInt();
        } while (result < low || result > high);
        return result;
    }

    public static int readInt() {
        return (new Scanner (System.in)).nextInt();
    }

    public static int readInt(String msg) {
        System.out.print(msg);
        return readInt();
    }

}


class MoveSol {

    public final String from;
    public final String to;

    public MoveSol(String from, String to){
        this.from = from;
        this.to = to;
    }

    public MoveSol(String move){
        this.from = "" +move.charAt(0)+move.charAt(1);
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
        return from + "" + to;
    }

    public boolean equals(Object other) {
        if (other.getClass() != this.getClass()) {
            return false;
        }
        return this.toString().equals(((MoveSol)other).toString());
    }

}

class PositionSol {

    // Player to move next
    public char next = 'W';

    // Animal placements, null-terminated
    private AnimalSol[] myAnimals;
    private int nrAnimals;

    private int nrSwitched; // Anzahl Spielrunden

    // Konstruktor
    public PositionSol() {
        myAnimals = new AnimalSol[65];
        nrAnimals = 0;
        nrSwitched = 0;
    }


    // Registriert ein Tier auf einem bestimmten Feld.
    public void placeAnimal(AnimalSol animal, String where) {
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
    public void removeAnimal(AnimalSol animal) {
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
    public void moveAnimal(AnimalSol animal, String from, String to) {
        removeAnimal(animal);
        placeAnimal(animal, to);
    }

    public void clearPos() {
        myAnimals = new AnimalSol[65];
        nrAnimals = 0;
        nrSwitched = 0;
    }

    public void reset(char movesNext) {
        clearPos();
        this.next = movesNext;
        placeAnimal(new PenguinSol(true), "a2");
        placeAnimal(new PenguinSol(true), "h2");
        placeAnimal(new PenguinSol(false), "a7");
        placeAnimal(new PenguinSol(false), "h7");
        placeAnimal(new SnakeSol(true), "a1");
        placeAnimal(new SnakeSol(true), "h1");
        placeAnimal(new SnakeSol(false), "a8");
        placeAnimal(new SnakeSol(false), "h8");
        placeAnimal(new ElephantSol(true), "b1");
        placeAnimal(new ElephantSol(true), "g1");
        placeAnimal(new ElephantSol(false), "b8");
        placeAnimal(new ElephantSol(false), "g8");
        placeAnimal(new HorseSol(true), "c1");
        placeAnimal(new HorseSol(true), "f1");
        placeAnimal(new HorseSol(false), "c8");
        placeAnimal(new HorseSol(false), "f8");
        placeAnimal(new LeopardSol(true), "e1");
        placeAnimal(new LeopardSol(true), "d1");
        placeAnimal(new LeopardSol(false), "d8");
        placeAnimal(new LeopardSol(false), "e8");
        String[] rs = {"b2","c2","d2","e2","f2","g2"};
        for (String s : rs) placeAnimal(new RabbitSol(true), s);
        String[] rsm = {"b7","c7","d7","e7","f7","g7"};
        for (String s : rsm) placeAnimal(new RabbitSol(false), s);
        nrSwitched = 0;
    }


    public AnimalSol[] getAnimals() {
        //return java.util.Arrays.copyOfRange(myAnimals,0,nrAnimals);
        AnimalSol[] tmp = new AnimalSol[nrAnimals];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = myAnimals[i];
        }
        return tmp;
    }

    public MoveSol[] possibleMoves(){
        int nrMoves = 0;
        MoveSol[] allMoves = new MoveSol[64*64];
        for (AnimalSol a : this.getAnimals()) {
            if ((next == 'W') == a.female) {
                MoveSol[] mv = a.possibleMoves();
                for (MoveSol m : mv) {
                    allMoves[nrMoves++] = m;
                }
            }
        }
        //return java.util.Arrays.copyOfRange(allMoves,0,nrMoves).sort(...);
        MoveSol[] tmp = new MoveSol[nrMoves];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = allMoves[i];
        }
        // bubblesort
        for(int i = 0; i < tmp.length; i++){
            for(int j = tmp.length-1; j > i; j--){
                if (tmp[i].from.compareTo(tmp[j].from)>0
                 || (tmp[i].from.equals(tmp[j].from) && tmp[i].to.compareTo(tmp[j].to)>0)) {
                    MoveSol temp = tmp[i];
                    tmp[i] = tmp[j];
                    tmp[j] = temp;
                }
            }
        }
        return tmp;
    }

    public boolean isLegalMove(MoveSol move){
        for (MoveSol m : possibleMoves()) {
            if (m.equals(move)) {
                return true;
            }
        }
        return false;
    }

    public void showMoves(){
        System.out.println("Mögliche Züge:");
        int newline = 0;
        for (MoveSol m : possibleMoves()) {
            System.out.print(m + " ");
            newline++;
            if (newline % 12 == 0) System.out.println();
        }
        System.out.println();
    }

    public AnimalSol getAnimalOnSquare(String square){
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

    private void applyMove(MoveSol move){
        AnimalSol animal = getAnimalOnSquare(move.from);
        AnimalSol prey = getAnimalOnSquare(move.to);
        if (prey != null) {
            removeAnimal(prey);
            ((PredatorSol)animal).eatPrey();
        }
        moveAnimal(animal, move.from, move.to);
    }


    public void applyMoves(MoveSol[] move){
        for (int i = 0; i < move.length; i++) {
            if (!isLegalMove(move[i])) {
                throw new RuntimeException("  >> Illegal move: " + move[i].toString());
            }
        }
        for (int i = 0; i < move.length; i++) {
            this.applyMove(move[i]);
        }
        this.switchSideToMove();
        if (nrSwitched % 2 == 0) {
            for (AnimalSol a : this.getAnimals()) {
                a.sunset();
            }
        }
    }

    public boolean gameOver() {
        char winner = theWinner();
        return winner == 'W' || winner == 'M' || winner == 'N';
    }

    public char theWinner() {
        AnimalSol[] remainingAnimals = getAnimals();
        if (remainingAnimals.length == 0){
            return 'N';
        }
        int nrPredators = 0, nrM = 0, nrW = 0;
        for (AnimalSol a : remainingAnimals) {
            if (a instanceof PredatorSol) nrPredators++;
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

    public AnimalSol[][] boardRepresentation(){
        AnimalSol[][] a = new AnimalSol[8][8];
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
        AnimalSol[][] ani = boardRepresentation();
        for (int i : I) {
            str += (i+" ");
            for (String j : J) {
                if (null == ani[toIndex(j)][i-1]) {
                    str += (i+toIndex(j))%2==1 ? GlobalsSol.ts_empty_square_dark : GlobalsSol.ts_empty_square_light;
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


public class TestAnimalBoardGame {
//TODO Kommentierung entfernen...
//     public static Move[] possibleMoves(Position pos){
//         int nrMoves = 0;
//         Move[] allMoves = new Move[63*63];
//         for (Animal a : pos.myAnimals) {
//             if (null != a && (pos.next == 'W') == a.female) {
//                 Move[] mv = a.possibleMoves();
//                 for (Move m : mv) {
//                     allMoves[nrMoves++] = m;
//         }   }   }
//         return java.util.Arrays.copyOfRange(allMoves,0,nrMoves);
//     }



    private static boolean occursFrom(String square, MoveSol[] moves, int nrMoves) {
        for (int i = 0; i < nrMoves; i++) {
            if (square.equals(moves[i].from)) return true;
        }
        return false;
    }
    private static boolean occursTo(String square, MoveSol[] moves, int nrMoves) {
        for (int i = 0; i < nrMoves; i++) {
            if (square.equals(moves[i].to)) return true;
        }
        return false;
    }
    public static boolean getANSI(){
        Position pos = new Position();
        pos.reset('M');
        int y = 0;
        String str = pos.toString();
        for (int i= 0; i < str.length(); i++) {
            if (str.charAt(i) == 'R') y++;
            if (str.charAt(i) == 'r') y++;
            if (y > 9) return false;
        }
        return true;
    }
    public static Move[] tom(String[] ms){
        Move[] m = new Move[ms.length];
        for (int i = 0; i < m.length; i++) {
            m[i]=new Move(ms[i]);
        }
        return m;
    }
    public static MoveSol[] tomSol(String[] ms){
        MoveSol[] m = new MoveSol[ms.length];
        for (int i = 0; i < m.length; i++) {
            m[i]=new MoveSol(ms[i]);
        }
        return m;
    }
    public static boolean contains(Move[] m, String move){
        for (Move x:m)if(x.toString().equals(move))return true;return false;
    }
    public static boolean contains(MoveSol[] m, String move){
        for (MoveSol x:m)if(x.toString().equals(move))return true;return false;
    }
    // start left / eat left L S P
    private static final int[] shdwpl={5,9,12,4,8,11};
    private static final String[] testLeo={ // + "" / 'M'
        "e2e3", "e7e6",
        "d1h5", "d8g5",
        "h5f7", "b7b6",
        "f7g7", "g5g2"
    };
    private static final String[] testPeng={
        "a2a3", "a7a6",
        "b2b3", "b7b6",
        "a3a4", "a6a5",
        "b3b4", "b6b5",
        "a4b5", "h7h6",
        "h2h3", "a5b4"
    };
    private static final String[] testSnake={
        "g2g3", "b7b6",
        "h1g6", "a8b3",
        "g6f7", "b3c2"
    };


    private static char rlg='W';
    private static int simulate(int index){
        int hm = 0;
        pos = new Position();
        posSol = new PositionSol();
        pos.reset(rlg);
        posSol.reset(rlg);
        if(rlg!='W'){
            pos.applyMoves(new Move[]{});
            posSol.applyMoves(new MoveSol[]{});
            hm++;
        }

        switch (index) { // LSP strt/lft
            case 3:
                for (int dummy = 0; dummy < 133; dummy++, hm++) {
                    if(dummy < testLeo.length){
                        pos.applyMoves(new Move[]{new Move(testLeo[dummy])});
                        posSol.applyMoves(new MoveSol[]{new MoveSol(testLeo[dummy])});
                    } else {
                        pos.applyMoves(new Move[]{});
                        posSol.applyMoves(new MoveSol[]{});
                    }
                    if(!pos.toString().equals(posSol.toString())){break;}
                }
                break;

            case 4:
                for (int dummy = 0; dummy < 133; dummy++, hm++) {
                    if(dummy < testSnake.length){
                        pos.applyMoves(new Move[]{new Move(testSnake[dummy])});
                        posSol.applyMoves(new MoveSol[]{new MoveSol(testSnake[dummy])});
                    } else {
                        pos.applyMoves(new Move[]{});
                        posSol.applyMoves(new MoveSol[]{});
                    }
                    if(!pos.toString().equals(posSol.toString())){break;}
                }
                break;

            case 5:
                for (int dummy = 0; dummy < 133; dummy++, hm++) {
                    if(dummy < testPeng.length){
                        pos.applyMoves(new Move[]{new Move(testPeng[dummy])});
                        posSol.applyMoves(new MoveSol[]{new MoveSol(testPeng[dummy])});
                    } else {
                        pos.applyMoves(new Move[]{});
                        posSol.applyMoves(new MoveSol[]{});
                    }
                    if(!pos.toString().equals(posSol.toString())){break;}
                }
                break;

            default:
                for(int dummy = 0; dummy < 133; dummy++){
                    pos.applyMoves(new Move[]{});
                    posSol.applyMoves(new MoveSol[]{});
                    if(!pos.toString().equals(posSol.toString())){break;}
                    hm++;
                }
                break;
        }
        return hm;
    }
    private static int bnrqx;
    private static boolean[] berr=new boolean[6];
    private static void checkrlz(int index){
        while(true){
        index=index%6;
        int next = (index+1)%6;
        int max = simulate(index);
        if(max>122){
            System.out.print(".");
            if(!berr[index]){
                berr[index]=true;
                bnrqx++;
                if(bnrqx==6)return;
            }
        }
        if((bnrqx<3&&index>2)||(bnrqx>=3&&index<=2)){
            index++;
            continue;
        }
        //System.out.print(" idx, max: " + index + " " + max);
        int txz=max-1;
        if(index==0){ // LSP strt/lft
            LeopardSol.start--;
            if(LeopardSol.start>0)txz = simulate(index);
            //System.out.print(" "+txz+" "+LeopardSol.start);
            if(txz>max){
                //if(txz>max)System.out.print("  checkrlz: LeopardSol.start-- ");
                max=txz;
            } else {
                LeopardSol.start += 2;
                txz = simulate(index);
                if(txz>=max){
                    //if(txz>max)System.out.print("  checkrlz: LeopardSol.start++ ");
                    max=txz;
                } else {
                    LeopardSol.start--;
                }
            }
        }
        if(index==1){
            SnakeSol.start--;
            if(SnakeSol.start>0)txz = simulate(index);
            //System.out.print(" "+txz+" "+SnakeSol.start);
            if(txz>max){
                //if(txz>max)System.out.print("  checkrlz: SnakeSol.start-- ");
                max=txz;
            } else {
                SnakeSol.start += 2;
                txz = simulate(index);
                if(txz>=max){
                    //if(txz>max)System.out.print("  checkrlz: SnakeSol.start++ ");
                    max=txz;
                } else {
                    SnakeSol.start--;
                }
            }
        }
        if(index==2){
            PenguinSol.start--;
            if(PenguinSol.start>0)txz = simulate(index);
            //System.out.print(" "+txz+" "+PenguinSol.start);
            if(txz>max){
                //if(txz>max)System.out.print("  checkrlz: PenguinSol.start-- ");
                max=txz;
            } else {
                PenguinSol.start += 2;
                txz = simulate(index);
                if(txz>=max){
                    //if(txz>max)System.out.println("  checkrlz: PenguinSol.start++ ");
                    max=txz;
                } else {
                    PenguinSol.start--;
                }
            }
        }
        if(index==3){
            if(LeopardSol.eat>1){
                LeopardSol.eat--;
                txz = simulate(index);
            }
           // System.out.print(" "+txz);
            if(txz>=max){
                //System.out.println("  checkrlz: LeopardSol.eat--");
                max=txz;
            } else {
                LeopardSol.eat += 2;
                txz = simulate(index);
                if(txz>=max){
                    //if(txz>max)System.out.println("  checkrlz: LeopardSol.eat++"+txz+" "+max);
                    max=txz;
                } else {
                    LeopardSol.eat--;
                }
            }
        }
        if(index==4){
            if(SnakeSol.eat>1){
                SnakeSol.eat--;
                txz = simulate(index);
            }
           // System.out.print(" "+txz);
            if(txz>=max){
                //System.out.println("  checkrlz: SnakeSol.eat--");
                max=txz;
            } else {
                SnakeSol.eat += 2;
                txz = simulate(index);
                if(txz>=max){
                    //if(txz>max)System.out.println("  checkrlz: SnakeSol.eat++"+txz+" "+max);
                    max=txz;
                } else {
                    SnakeSol.eat--;
                }
            }
        }
        if(index==5){
            if(PenguinSol.eat>1){
                PenguinSol.eat--;
                txz = simulate(index);
            }
           // System.out.print(" "+txz);
            if(txz>=max){
                //System.out.println("  checkrlz: PenguinSol.eat--");
                max=txz;
            } else {
                PenguinSol.eat += 2;
                txz = simulate(index);
                if(txz>=max){
                    //if(txz>max)System.out.println("  checkrlz: PenguinSol.eat++"+txz+" "+max);
                    max=txz;
                } else {
                    PenguinSol.eat--;
                }
            }
        }
        //System.out.println();
        index++;
        }
    }

    private static String timi(int x){String y="";for(int i=0;i<x;i++)y+=" ";return y;}
    private static int qr67(int j){int[]q={2,0,0,0,0,0,0,0,0,3,5};if(j>=q.length)return 20;return q[j];}
    private static String test(String s){
        String front= "   ",back="   ";
        String str = "", ey = front+back;
        String[] v = s.split("\n");
        int sfh=11>v.length?11:v.length+1;
        int w = 0, h = v.length, wm = 1;
        for (int i = 0; i < s.length(); i++) {
            if("\n".equals(s.charAt(i)+"")){
                w = 0;
            } else {
                w++;
                wm=wm>w?wm:w;
            }
        }
        for(int i=1;i<=wm;i++){ey+=" ";}
        int i=1,j=0;
        while(i<=(sfh-h)/2){str+=timi(qr67(i-1))+ey+"\n";i++;}
        for(;i<=(sfh-h)/2+h;){str+=timi(qr67(i-1))+front+v[j]+back+timi(wm-v[j].length())+"\n";i++;j++;}
        while(i<=sfh){str+=timi(qr67(i-1))+ey+"\n";i++;}
        return str;
    }
    public static String conc(String a, String d, String c){
        String b = test(d);
        String r = "";
        int ia=0,ib=0,ic=0;
        while(!(ia>=a.length()&&ib>=b.length()&&ic>=c.length())){
            while(ia<a.length()){if(!"\n".equals(a.charAt(ia)+""))r+=a.charAt(ia++);else{ia++;break;}}
            while(ib<b.length()){if(!"\n".equals(b.charAt(ib)+""))r+=b.charAt(ib++);else{ib++;break;}}
            while(ic<c.length()){if(!"\n".equals(c.charAt(ic)+""))r+=c.charAt(ic++);else{ic++;break;}}
            r+="\n";
        }
        return r;
    }

    private static final String eml="           \n";
    private static final String gmp="  "+eml+eml+eml+eml+eml+eml+eml+eml+eml+"   "+eml+"     "+eml;

    private static String[] ausgang = new String[20];
    private static int nraf(){
        for (int i = 0; i < ausgang.length; i++){
            if (null == ausgang[i]){
                return i;
            }
        }
        return ausgang.length;
    }
    private static int regis(String msg){
        for (int i = 0; i < ausgang.length; i++){
            if (null == ausgang[i]){
                ausgang[i] = new String(msg);
                return i;
            }
            if (ausgang[i].equals(msg)) {
                return -1;
            }
        }
        return 20;
    }

    public static void reportMismatch(String b, String bs, String msg){
        System.out.println(conc(b,msg,bs));
    }

    public static void msg(String message, boolean confirm){
        if(confirm){
            System.out.print("\n" + message + "   Zum Fortsetzen Returntaste drücken... ");
            String ignore = IOSol.readString("");
        } else {
            System.out.print("\n" + message + "\n\n");
        }
    }

    private static int direction(String mv){
        final int[] dir={0,1,2,3,4,5,6,7};
        int lr=new Character(mv.charAt(0)).compareTo(mv.charAt(2));
        int ud=new Character(mv.charAt(1)).compareTo(mv.charAt(3));
        if(lr<0){
            return ud==0? dir[0] : (ud<0? dir[1] : dir[2]);
        }
        if(lr>0){
            return ud==0? dir[3] : (ud<0? dir[4] : dir[5]);
        }
        return ud<0? dir[6] : dir[7];
    }
    private static boolean vegid(int id){
        return id==3||id==4||id==5;
    }
    private static int anid(String mv){
        AnimalSol a = posSol.getAnimalOnSquare(mv.charAt(0)+""+mv.charAt(1));
        if(a instanceof LeopardSol)return 0;
        if(a instanceof SnakeSol)return 1;
        if(a instanceof PenguinSol)return 2;
        if(a instanceof ElephantSol)return 3;
        if(a instanceof HorseSol)return 4;
        if(a instanceof RabbitSol)return 5;
        return -1;
    }
    private static boolean capt(int id){return id==1;}
    private static int capt(String mv){
        AnimalSol a = posSol.getAnimalOnSquare(mv.charAt(2)+""+mv.charAt(3));
        return a==null?0:1;
    }
    private static String anS(int an){
        if(an==0)return "Leopard";
        if(an==1)return "Schlange";
        if(an==2)return "Pinguin";
        if(an==3)return "Elefant";
        if(an==4)return "Pferd";
        return "Kaninchen";
    }
    private static String drS(int dr){
        if(dr==0)return "rechts";
        if(dr==1)return "rechts oben";
        if(dr==2)return "rechts unten";
        if(dr==3)return "links";
        if(dr==4)return "links oben";
        if(dr==5)return "links unten";
        if(dr==6)return "oben";
        return "unten";
    }
    private static String cpS(int cp){
        if(cp==1)return " (Schlagzug) ";
        return "";
    }
    private static final int[][][] testcase=new int[6][8][2];
    private static final int[][][] fail=new int[6][8][2];
    private static MoveSol[] filter(MoveSol[] orig){
        int num=0;
        MoveSol[] res=new MoveSol[orig.length];
        for(MoveSol o : orig){
            String mv = o.toString();
            if(fail[anid(mv)][direction(mv)][capt(mv)]<3){
                res[num]=o;
                num++;
            }
        }
        return java.util.Arrays.copyOfRange(res,0,num);
    }
    private static Move[] filter(Move[] orig){
        int num=0;
        Move[] res=new Move[orig.length];
        for(Move o : orig){
            String mv = o.toString();
            if(fail[anid(mv)][direction(mv)][capt(mv)]<3){
                res[num]=o;
                num++;
            }
        }
        return java.util.Arrays.copyOfRange(res,0,num);
    }

    private static void showHist(String[][] history, int moveCount, String board){
        for (int m = 0; m <= moveCount; m++) {
            System.out.print((m+1)+". ");
            for (int i = 0; i < history[m].length; i++) {
                System.out.print(history[m][i] + " ");
            }
            System.out.println();
        }
        System.out.println("\nSpielsituation: \n\n" + board);
    }
    public static boolean rmmContinue(String[][] history, char first, int diff, String b, String bsl){
        PositionSol posSol;
        Position pos;
        String boardSol = "boardSol";
        String board = "board";
        for (int fcf=0; fcf < history[diff].length; fcf++) {
            posSol = new PositionSol();
            pos = new Position();
            pos.reset(first);
            posSol.reset(first);
            for (int i=0; i < diff; i++) {
                MoveSol[] ms = new MoveSol[history[i].length];
                for(int j = 0; j < ms.length; j++){
                    ms[j]=new MoveSol(history[i][j]);
                }
                posSol.applyMoves(ms);
                Move[] mm = new Move[history[i].length];
                for(int j = 0; j < mm.length; j++){
                    mm[j]=new Move(history[i][j]);
                }
                pos.applyMoves(mm);
                board = pos.toString();
                boardSol = posSol.toString();
                if (!board.equals(boardSol)) {
                    showHist(history,i,boardSol);
                    System.out.println("\n\n    << Fatal: Nichtdeterministisches Verhalten (Zug Nr. " + (i+1) + ") -- Abbruch >>\n\n");
                    return false;
                }
            }
            String fs = history[diff][fcf];
            int cpt = 0, an = 5, dir = direction(fs);
            if(null!=posSol.getAnimalOnSquare(fs.charAt(2)+""+fs.charAt(3))){cpt++;}
            AnimalSol ani = posSol.getAnimalOnSquare(fs.charAt(0)+""+fs.charAt(1));
            if(ani instanceof LeopardSol) an=0;
            if(ani instanceof SnakeSol) an=1;
            if(ani instanceof PenguinSol) an=2;
            if(ani instanceof ElephantSol) an=3;
            if(ani instanceof HorseSol) an=4;
            posSol.applyMoves(new MoveSol[]{new MoveSol(fs)});
            pos.applyMoves(new Move[]{new Move(fs)});
            board = pos.toString();
            boardSol = posSol.toString();
            if (!board.equals(boardSol)) {
                fail[an][dir][cpt]++;
                testcase[an][dir][cpt]++;
                if(fail[an][dir][cpt]==1){
                    System.out.println();
                    showHist(history,diff,boardSol);//System.out.println("\n"+boardSol);
                    System.out.println("  F E H L E R: Erhalte die Spielsituation\n\n" + board + "\n");
                }
                return true;
            }
        }
        showHist(history,diff,boardSol);
        System.out.println("\n\n    << Fatal 2.0: Nichtdeterministisches Verhalten (Zug Nr. " + (1+diff) + ") -- Abbruch >>\n\n");
        return false;
    }

    private static PositionSol posSol;
    private static Position pos;

    public static void main(String args[]) {
        System.out.println(">>Globals.ANSI: " + GlobalsSol.ANSI);
        String boardSol="unused",board="board";
        String[][] chosen=new String[250][0];
        int mCount = 0;

        try{
        for (char lf = 'M'; lf != 'N'; lf=lf=='M'?'W':'N'){
            for (int rlxyz55=1;rlxyz55<=2;rlxyz55++){
                pos = new Position();
                pos.reset(lf);
                if(lf=='W'){pos.applyMoves(new Move[]{});}
                pos.applyMoves(new Move[]{new Move("c7c6"),new Move("e7e6")});
                pos.applyMoves(new Move[]{new Move("e2e3")});
                for(int i=1;i<=28*(rlxyz55-1);i++){pos.applyMoves(new Move[]{});}
                posSol = new PositionSol();
                posSol.reset(lf);
                if(lf=='W'){posSol.applyMoves(new MoveSol[]{});}
                posSol.applyMoves(new MoveSol[]{new MoveSol("c7c6"),new MoveSol("e7e6")});
                posSol.applyMoves(new MoveSol[]{new MoveSol("e2e3")});
                for(int i=1;i<=28*(rlxyz55-1);i++){posSol.applyMoves(new MoveSol[]{});}
                boardSol = posSol.toString();
                board = pos.toString();
                String b42=board, bsol=boardSol;
                pos.reset(lf);
                if(lf=='W'){pos.applyMoves(new Move[]{});}
                pos.applyMoves(new Move[]{new Move("c7c6"),new Move("e7e6")});
                pos.applyMoves(new Move[]{new Move("e2e3")});
                for(int i=1;i<=28*(rlxyz55-1);i++){pos.applyMoves(new Move[]{});}
                posSol.reset(lf);
                if(lf=='W'){posSol.applyMoves(new MoveSol[]{});}
                posSol.applyMoves(new MoveSol[]{new MoveSol("c7c6"),new MoveSol("e7e6")});
                posSol.applyMoves(new MoveSol[]{new MoveSol("e2e3")});
                for(int i=1;i<=28*(rlxyz55-1);i++){posSol.applyMoves(new MoveSol[]{});}
                boardSol=posSol.toString();
                board = pos.toString();
                // //Test
                // String tmprl2=rlxyz55==1?"\n":"\n+14 Runden passen (beide)\n";
                // System.out.println("\n Nichtdeterministisches Verhalten! \n");
                // String pl2=lf=='W'?"1.\n2. c7c6 e7e6\n3. e2e3":"1. c7c6 e7e6\n2. e2e3";
                // reportMismatch(b42,board,"   Nach\n"+ pl2 +" "+tmprl2+"\n   <==>");
                // System.out.println("\n Abbruch :-( \n");
                // if(true) return;
                if (!bsol.equals(boardSol)){
                    System.out.println("\noups  Testprogramm tut nicht...\n");
                    return;
                } else if (!b42.equals(board)){
                    pos = new Position();
                    pos.reset(lf);
                    if(lf=='W'){pos.applyMoves(new Move[]{});}
                    pos.applyMoves(new Move[]{new Move("c7c6"),new Move("e7e6")});
                    pos.applyMoves(new Move[]{new Move("e2e3")});
                    for(int i=1;i<=28*(rlxyz55-1);i++){pos.applyMoves(new Move[]{});}
                    posSol.reset(lf);
                    if(lf=='W'){posSol.applyMoves(new MoveSol[]{});}
                    posSol.applyMoves(new MoveSol[]{new MoveSol("c7c6"),new MoveSol("e7e6")});
                    posSol.applyMoves(new MoveSol[]{new MoveSol("e2e3")});
                    for(int i=1;i<=28*(rlxyz55-1);i++){posSol.applyMoves(new MoveSol[]{});}
                    boardSol=posSol.toString();
                    board = pos.toString();
                    if (!b42.equals(board)){
                        String tmprl=rlxyz55==1?"\n":"\n+14 Runden passen (beide)\n";
                        System.out.println("\n Nichtdeterministisches Verhalten! \n");
                        String pl=lf=='W'?"1.\n2. c7c6 e7e6\n3. e2e3":"1. c7c6 e7e6\n2. e2e3";
                        reportMismatch(b42,board,"   Nach\n"+ pl +" "+tmprl+"\n   <==>");
                        System.out.println("\n Abbruch :-( \n");
                        return;
                    } else {
                        msg(" reset scheint nicht voll zu funktionieren, d.h."
                        +" mit selbem Objekt kann nur einmal gespielt werden.",true);
                    }
                }
            }
        }

        // test Snake.daysToLiveWithoutPrey + sunset()
        rlg='W';
        //checkrlz(java.util.Arrays.copyOfRange(shdwpl,0,shdwpl.length),0,false);
        int max = simulate(1);
        bnrqx=0;
        berr=new boolean[6];
        System.out.print("  Potentielle Endlosschleife:\n");
        System.out.print("  Versuche, Restlaufzeiten zu ermitteln...");
        ///LeopardSol.start=5;LeopardSol.eat=5;PenguinSol.start=12;PenguinSol.eat=12;SnakeSol.start=9;SnakeSol.eat =9;
        checkrlz(0);
        System.out.println("fertig.\n");
// System.out.println("values (L S P start / eat): " + LeopardSol.start +","+ SnakeSol.start +","+ PenguinSol.start
//         +" ;; "+ LeopardSol.eat +","+ SnakeSol.eat +","+ PenguinSol.eat+"\n");
        int nrlzf=0;
        if(LeopardSol.start!=5){
            System.out.println("\n  Fehler: Leopard sollte mit RLZ 5 starten, startet mit RLZ "+LeopardSol.start+"\n");
            nrlzf++;
        }
        if(SnakeSol.start!=9){
            System.out.println("\n  Fehler: Schlange sollte mit RLZ 9 starten, startet mit RLZ "+SnakeSol.start+"\n");
            nrlzf++;
        }
        if(PenguinSol.start!=12){
            System.out.println("\n  Fehler: Pinguin sollte mit RLZ 12 starten, startet mit RLZ "+PenguinSol.start+"\n");
            nrlzf++;
        }
        if(LeopardSol.eat!=5){
            System.out.println("\n  Fehler: Leopard sollte nach Fressen mit RLZ 5 weitermachen, macht mit RLZ "+LeopardSol.eat+" weiter\n");
            nrlzf++;
        }
        if(SnakeSol.eat!=9){
            System.out.println("\n  Fehler: Schlange sollte nach Fressen mit RLZ 9 weitermachen, macht mit RLZ "+SnakeSol.eat+" weiter\n");
            nrlzf++;
        }
        if(PenguinSol.eat!=12){
            System.out.println("\n  Fehler: Pinguin sollte nach Fressen mit RLZ 12 weitermachen, macht mit RLZ "+PenguinSol.eat+" weiter\n");
            nrlzf++;
        }
        if(LeopardSol.start > 40 || SnakeSol.start > 40 || PenguinSol.start > 40 || LeopardSol.eat > 40 || SnakeSol.eat > 40 || PenguinSol.eat > 40){
            System.out.println("\n\n    Restlaufzeiten: Abbruch \n\n   Restlaufzeiten konnten nicht angepasst werden.");
            System.out.println("   Müssen im Quelltext korrigiert und Testprogramm neu gestartet werden.\n");
            return;
        }
        System.out.print("Starte Tests..");
        playGame : for (int runs = 1; runs <= 1500; runs++) {
            System.out.print(".");
            pos = new Position();
            posSol = new PositionSol();
            char first = myRandom(1,2) == 1 ? 'W' : 'M';
            pos.reset(first);
            posSol.reset(first);
            boardSol=posSol.toString();
            board = pos.toString();
//             System.out.println("starting game (movesNext = '" + posSol.next + "')");
            mCount = 0;
            chosen=new String[250][0];
            makeMoves : while (true) {

//                 System.out.println(boardSol);
//                 posSol.showMoves();

                MoveSol[] pms = filter(posSol.possibleMoves());

//TODO Kommentierung entfernen...
//                 Move[] pm = filter(possibleMoves(pos));
// 
//                 int fails = 0;
//                 for (int i=0; i < pm.length; i++) {
//                     if (!contains(pms,pm[i].toString())) {
//                         fails=i+1;
//                         break;
//                     }
//                 }
//                 if (fails > 0) {
//                     Move failed = pm[(fails-1)];
//                     String fs = failed.toString();
//                     int an = anid(fs), dir = direction(fs), cpt = capt(fs);
//                     fail[an][dir][cpt]++;
//                     testcase[an][dir][cpt]++;
//                     if(fail[an][dir][cpt]==1){
//                         System.out.println();
//                         showHist(chosen,mCount,boardSol);
//                         System.out.println("  E R R O R : Move " + failed + " should not be possible but is...\n\n");
//                     }
//                 }
//                 fails = 0;
//                 for (int i=0; i < pms.length; i++) {
//                     if (!contains(pm,pms[i].toString())) {
//                         fails=i+1;
//                         break;
//                     }
//                 }
//                 if (fails > 0) {
//                     MoveSol failed = pms[(fails-1)];
//                     String fs = failed.toString();
//                     int an = anid(fs), dir = direction(fs), cpt = capt(fs);
//                     fail[an][dir][cpt]++;
//                     testcase[an][dir][cpt]++;
//                     if(fail[an][dir][cpt]==1){
//                         System.out.println();
//                         showHist(chosen,mCount,boardSol);
//                         System.out.println("  E R R O R : Move " + failed + " should be possible but is not!\n\n");
//                     }
//                 }



                boolean[] choice = new boolean[pms.length];
                for (int i=0; i<choice.length; i++) choice[i]=false;
                int maxNrMoves = myRandom(0,4), nrM=0;
                boolean predatorMove = false;
                MoveSol[] moves = new MoveSol[4];
                for (int i=1; i<= maxNrMoves; i++){
                    int nextChoice=myRandom(0,choice.length-1);
                    if (choice.length != 0 && (!choice[nextChoice])){
                        MoveSol cnd = pms[nextChoice];
                        if (occursFrom(cnd.from, moves, nrM) || occursTo(cnd.to, moves, nrM)) {
                            break;
                        } else if (posSol.getAnimalOnSquare(cnd.from) instanceof PredatorSol) {
                            if (predatorMove) {
                                break;
                            }
                            predatorMove = true;
                        } else if (nrM == 3 && !predatorMove) {
                            break;
                        }
                        choice[nextChoice]=true;
                        moves[nrM]=cnd;
                        nrM++;
                    }
                }
                chosen[mCount]=new String[nrM];
                for (int i = 0; i < nrM; i++) {
                    String mstr = moves[i].toString();
                    chosen[mCount][i]=new String(mstr);
                    testcase[anid(mstr)][direction(mstr)][capt(mstr)]++;
                }

                // System.out.print((mCount+1) + ". ");
                // if (nrM == 0) {
                //     System.out.println();
                // } else {
                //     for (String move : chosen[mCount]) if(null!=move) System.out.print(move+"  ");
                // }
                // System.out.println();

                posSol.applyMoves(tomSol(chosen[mCount]));
                pos.applyMoves(tom(chosen[mCount]));
                boardSol=posSol.toString();
                board = pos.toString();
                if (!boardSol.equals(board)) {
                    if (!rmmContinue(chosen, first, mCount, board, boardSol)) {
                        return;
                    }
                }

                mCount++;
                char ppw = pos.theWinner();
                char winner = posSol.theWinner();
                if (posSol.gameOver()) {
                    if(ppw == winner){
                        break makeMoves;
                    }

                    String errMsg = "  Fehler: Spielausgang:  erwarte '" + winner + "', ist: '" + ppw + "'";
                    int idf = regis(errMsg);
                    if (idf==19) {
                        System.out.println("\n"+errMsg+"\n\n  << Zu viele Spielausgangsfehler >>\n");
                    } else if (idf>=0 && idf < 20) {
                        System.out.println("\n"+errMsg+"\nVerlauf:\n");
                        showHist(chosen,mCount,boardSol);
                        System.out.println();
                    }

                    break makeMoves;

                } else if (ppw != 'X') {

                    String errMsg = "  Fehler: Spielausgang:  erwarte '" + winner + "', bekomme: '" + ppw + "'";
                    int idf = regis(errMsg);
                    if (idf==19) {
                        System.out.println("\n"+errMsg+"\n\n  << Zu viele Spielausgangsfehler >>\n");
                    } else if (idf>=0 && idf < 20) {
                        System.out.println("\n"+errMsg+"\nVerlauf:\n");
                        showHist(chosen,mCount,boardSol);
                        System.out.println();
                    }

                    break makeMoves;
                }
            }
        }

        System.out.println("fertig.\n");
        boolean fehl=nrlzf!=0;
        System.out.println("  Aufgetretene Fehler:\n");
        // RLZ
        if (nrlzf!=0){
            System.out.println("  ==> " + nrlzf + " Fälle falscher Restlaufzeiten.\n");
        }
        // Zuege
        for(int an = 0; an < 6; an++){
            for(int dir = 0; dir < 8; dir++){
                for(int cpt = 0; cpt < 2; cpt++){
                    if (!(vegid(an) && capt(cpt))) {
                        if(fail[an][dir][cpt]>0){fehl=true;}
                        if(fail[an][dir][cpt]>0) {
                            System.out.println("Zug mit "+anS(an)+" nach "+drS(dir)+cpS(cpt)+": "
                                +(testcase[an][dir][cpt]-fail[an][dir][cpt])
                                +" von "+(testcase[an][dir][cpt])+" Testfällen korrekt.");
                        }
                    }
                }
            }
        }
        // Spielausgang
        int ausfehl = nraf();
        if(ausfehl>0){
            System.out.println("  ==> " + ausfehl + " Arten von Spielausgangsfehlern.\n");
            fehl=true;
        }
        if(!fehl)System.out.println("  Keine.");

        } catch (RuntimeException rte){
            System.out.println("\n  Verlauf bis zur RuntimeException:\n");
            showHist(chosen,mCount,boardSol);
            throw rte;
        }

    }

    // Zufallszahl zwischen low und high
    private static int myRandom(int low, int high) {
        return (int) (Math.random() * (high + 1 - low) + low);
    }
}
