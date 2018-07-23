public class Snake extends Predator {

    public Snake(boolean female) {
        super(9, female); // Schlange kann 9 Tage bzw. Spielrunden ohne Essen auskommen.
    }


    /**
     * Ermittelt die moeglichen Zuege gemaess den Spielregeln,
     * die das Tier von seinem Feld aus in der aktuellen Position
     * ausfuehren kann.
     *
     * Muss von jeder einzelnen Tierklasse ueberschrieben werden.
     */
    public Move[] possibleMoves() {
        int last = -1;
        String to;
        Move[] myMoves = new Move[63];

        int[] x = {1, 1, -1,1,  1,-1, -1,-1};
        int[] y = {1,-1,  1,1, -1,-1, -1, 1};
        int[] z = {0,2,4,6};
        for (int i : z) {
            to = Move.step(this.square,x[i],y[i]);
            while (null != to) {
                boolean moveOK = false;
                Animal other = this.position.getAnimalOnSquare(to);
                if (other == null) {
                    moveOK = true;
                } else if (other instanceof Vegetarian && this.female != other.female) {
                    moveOK = true;
                }
                if (moveOK) {
                    myMoves[++last] = new Move(this.square, to);
                    i++; if (i % 2 == 0) i-=2;
                    to = Move.step(to,x[i],y[i]);
                }
                if (!moveOK || other != null) {
                    break;
                }
            }
        }

        //return java.util.Arrays.copyOfRange(myMoves,0,last+1);
        Move[] tmp = myMoves;
        myMoves = new Move[last+1];
        for (int i = 0; i < myMoves.length; i++) {
            myMoves[i] = tmp[i];
        }
        return myMoves;
    }


    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_snake_dark : Globals.ts_female_snake_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_snake_dark : Globals.ts_male_snake_light);
    }

}
