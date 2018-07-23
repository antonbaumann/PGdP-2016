public class Horse extends Vegetarian {

    public Horse(boolean female) {
        super(female);
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

        int[] x = {-1,0,1,1,1,0,-1,-1};
        int[] y = {1,1,1,0,-1,-1,-1,0};
        for (int i = 0; i < x.length; i++) {
            if ((x[i]+2+y[i])%2==0) {
                to = Move.step(this.square,x[i],y[i]);
                if (null != to) to = Move.step(to,x[i],y[i]);
                if (null != to) {
                    if (this.position.getAnimalOnSquare(to) == null) {
                        myMoves[++last] = new Move(this.square, to);
                    }
                    to = Move.step(to,x[i],y[i]);
                }
                if (null != to && this.position.getAnimalOnSquare(to) == null) {
                    myMoves[++last] = new Move(this.square, to);
                }
            } else {
                to = Move.step(this.square,x[i],y[i]);
                if (null != to) {
                    if (this.position.getAnimalOnSquare(to) == null) {
                        myMoves[++last] = new Move(this.square, to);
                    }
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
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_horse_dark : Globals.ts_female_horse_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_horse_dark : Globals.ts_male_horse_light);
    }

}
