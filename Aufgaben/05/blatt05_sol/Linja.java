package pgdpws16;

public class Linja extends MiniJava {

    private static int[][] spielfeld = new int[8][6];

    /**
     * initialisiert das Spielfeld Ziellinie fuer Spieler 1 ist Zeile 7
     * Ziellinie fuer Spieler -1 ist Zeile 0
     */
    private static void initSpiel() {
        for (int i = 0; i < spielfeld.length; i++) {
            if (i != 0 && i != spielfeld.length - 1) {
                spielfeld[i] = new int[]{-(12 - i + 1), 0, 0, 0, 0, 6 + i};
            }
            if (i == 0) {
                spielfeld[i] = new int[]{1, 2, 3, 4, 5, 6};
            }
            if (i == spielfeld.length - 1) {
                spielfeld[i] = new int[]{-6, -5, -4, -3, -2, -1};
            }
        }

    }

    /**
     *
     * @return formatiertes aktuelles Spielfeld
     */
    private static String output() {
        String tmp = "Spieler 1 spielt von oben nach unten\n"
                + "Spieler -1 spielt von unten nach oben\n";
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                tmp = tmp + "\t" + spielfeld[i][j];
            }
            tmp = tmp + "\n";
        }
        return tmp;
    }

    /**
     * @return true, wenn die Eingabe stein im richtigen Wertebereich liegt und
     * zum Spieler gehoert; false, sonst
     */
    private static boolean gueltigeEingabe(int stein, int spieler) {
        if (spieler == 1) {
            if (stein <= 0 || stein > 12) {
                write("Ungueltiger Stein. Sie koennen die Steine 1 bis 12 waehlen.");
                return false;
            }
            return true;
        }
        if (spieler == -1) {
            if (stein >= 0 || stein < -12) {
                write("Ungueltiger Stein. Sie koennen die Steine -1 bis -12 waehlen.");
                return false;
            }
            return true;
        }
        /*
         ist spieler weder 1 noch -1 wird nur der Wertebereich von stein ueberprueft
         (optionale Bonusregel)
         */
        if (stein < -12 || stein == 0 || stein > 12) {
            write("Ungueltiger Stein. Im Bonuszug koennen Sie die Steine 1 bis 12 oder -1 bis -12 waehlen.");
            return false;
        }
        return true;
    }

    /**
     * @param stein kann Werte -1 bis -12 und 1 bis 12 haben
     * @return gibt x-Koordinate von stein an Position 0 und die y-Koordinaten
     * von stein an Position 1 zurueck; falls stein nicht gefunden, wird {-1,-1}
     * zurueckgegeben
     */
    private static int[] findeStein(int stein) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                if (spielfeld[i][j] == stein) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * @param reihe hat Werte 0 bis 7
     * @return Anzahl der Steine in einer Reihe
     */
    private static int steineInReihe(int reihe) {
        int anzahl = 0;
        for (int j = 0; j < 6; j++) {
            if (spielfeld[reihe][j] != 0) {
                anzahl++;
            }
        }
        return anzahl;
    }

    /**
     * Ueberprueft, ob der Zug zulaessig ist und fuehrt diesen aus, wenn er
     * zulaessig ist.
     *
     * @param vorwaerts == true: Zug erfolgt vorwaerts aus Sicht des
     * Spielers/Steins vorwearts == false: Zug erfolgt rueckwaerts aus Sicht des
     * Spielers/Steins
     * @return Rueckgabe -1: Zug nicht zulaessig Rueckgabe 0-5: Weite des
     * potentiellen naechsten Zugs (falls Folgezug folgt) Rueckgabe 6: Ziellinie
     * wurde genau getroffen (potentieller Bonuszug)
     *
     */
    private static int setzeZug(int stein, int weite, boolean vorwaerts) {

        int[] position = findeStein(stein);
        int posX = position[0];
        int posY = position[1];
        int weiteNext = 0;

        // Richtung wird entsprechend Spieler (Steine positiv/negativ) angepasst
        int richtung;
        if (stein > 0) {
            richtung = 1;
        } else {
            richtung = -1;
        }
        if (!vorwaerts) {
            richtung = richtung * -1;
        }

        // Stein hat Ziellinie erreicht
        if (posX == -1) {
            // vorwaerts Zug ist ungueltig
            if (vorwaerts) {
                write("Zug ausserhalb des Spielfelds. Stein ist schon auf Ziellinie.\n"
                        + "Stein kann nur rueckwarts bewegt werden.");
                return -1;
            }
            // Stein befindet sich auf Ziellinie
            posX = stein > 0 ? 7 : 0;
        }

        // Reihe, in der Stein platziert werden soll, wird berechnet
        int naechsteReihe = posX + richtung * weite;
        //System.out.println("naechsteReihe = " + naechsteReihe);

        // Ziellinie genau getroffen Spieler 1
        if (stein > 0 && naechsteReihe == 7) {
            weiteNext = 6;
        }
        // Ziellinie genau getroffen Spieler -1
        if (stein < 0 && naechsteReihe == 0) {
            weiteNext = 6;
        }
        // Zug ausserhalb des Spielfelds ueber Ziellinie steine < 0
        if (naechsteReihe < 0) {
            if (stein < 0) {
                weiteNext = 0;
            } else {
                write("Zug ausserhalb des Spielfelds.");
                return -1;
            }
        }
        // Zug ausserhalb des Spielfelds ueber Ziellinie steine > 0
        if (naechsteReihe > 7) {
            if (stein > 0) {
                weiteNext = 0;
            } else {
                write("Zug ausserhalb des Spielfelds.");
                return -1;
            }

        }
        // Zug innerhalb des Spielfelds und nicht auf die Ziellinie des steins
        if (weiteNext != 6 && naechsteReihe >= 0 && naechsteReihe <= 7) {

            if (steineInReihe(naechsteReihe) > 5) {
                write("In der Zielreihe sind schon sechs Steine.");
                return -1;
            }
            weiteNext = steineInReihe(naechsteReihe);
        }

        // Fuehre Zug aus
        if (posY != -1) {
            spielfeld[posX][posY] = 0;
        }
        if ((stein > 0 && naechsteReihe < 7) || (stein < 0 && naechsteReihe > 0)) {
            int i = 0;
            while (spielfeld[naechsteReihe][i] != 0) {
                i++;
            }
            spielfeld[naechsteReihe][i] = stein;
        }

        return weiteNext;

    }

    /**
     * @return true, falls die Bedingungen des Spielendes erfuellt sind, d.h.
     * alle Steine des einen Spielers sind an den Steinen des gegnerischen
     * Spielers vorbeigezogen
     *
     */
    private static boolean spielende() {

        int reihe_1 = 0;
        int reihe1 = 7;
        /* alternativ - in den Regeln ist nicht klar spezifiziert, ob zwei Steine
         auf der Ziel- (Spieler A) und Startlinie (Spieler B) aneinander
         vorbei gezogen sind */
        // int reihe_1 = -1;
        // int reihe1 = 8;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                if (spielfeld[i][j] < 0 && i > reihe_1) {
                    reihe_1 = i;
                }
                if (spielfeld[i][j] > 0 && i < reihe1) {
                    reihe1 = i;
                }
            }
        }
        return reihe_1 < reihe1;
    }

    /**
     * zaehlt die Punkte der beiden Spieler und gibt das Ergebnis aus
     */
    private static void zaehlePunkte() {

        int pkt_1 = 0;
        int minuspkt_1 = 0;
        int steine_1 = 0;
        int pkt1 = 0;
        int minuspkt1 = 0;
        int steine1 = 0;

        for (int j = 0; j < 6; j++) {
            if (spielfeld[0][j] < 0) {
                pkt_1 = pkt_1 + 5;
                steine_1++;
            }
            if (spielfeld[0][j] > 0) {
                minuspkt1 = minuspkt1 + 5;
                steine1++;
            }
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (spielfeld[i][j] < 0) {
                    pkt_1 = pkt_1 + 4 - i;
                    steine_1++;
                }
                if (spielfeld[i][j] > 0) {
                    minuspkt1 = minuspkt1 + 4 - i;
                    steine1++;
                }
            }
        }

        for (int i = 4; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (spielfeld[i][j] < 0) {
                    minuspkt_1 = minuspkt_1 + i - 3;
                    steine_1++;
                }
                if (spielfeld[i][j] > 0) {
                    pkt1 = pkt1 + i - 3;
                    steine1++;
                }
            }
        }

        for (int j = 0; j < 6; j++) {
            if (spielfeld[7][j] > 0) {
                pkt1 = pkt1 + 5;
                steine1++;
            }
            if (spielfeld[7][j] < 0) {
                minuspkt_1 = minuspkt_1 + 5;
                steine_1++;
            }
        }

        int punkte_1 = pkt_1 - minuspkt_1 + (12 - steine_1) * 5;
        int punkte1 = pkt1 - minuspkt1 + (12 - steine1) * 5;

        if (punkte1 > punkte_1) {
            write("Spieler 1: " + punkte1 + " Punkte."
                    + " Spieler -1: " + punkte_1 + " Punkte."
                    + " Spieler1 hat gewonnen!");
        }
        if (punkte1 < punkte_1) {
            write("Spieler 1: " + punkte1 + " Punkte."
                    + " Spieler -1: " + punkte_1 + " Punkte."
                    + " Spieler-1 hat gewonnen!");
        }
        if (punkte1 == punkte_1) {
            write("Spieler 1: " + punkte1 + " Punkte."
                    + " Spieler -1: " + punkte_1 + " Punkte."
                    + " Unentschieden!");
        }

    }

    /**
     * Spielablauf entsprechend Anfangszug, Folgezug, Bonuszug
     *
     * @param spieler ist 1 (Spielsteine 1 bis 12) oder -1 (Spielsteine -1 bis
     * -12)
     * @param bonus == true: Bonusregel ist aktiv, d.h. im Bonuszug kann auch
     * ein gegnerischer Stein gezogen werden
     */
    private static void spielerZieht(int spieler, boolean bonus) {

        // Beginn Anfangszug
        int stein;
        int weite = -1;
        do {
            stein = readInt("Spieler " + spieler
                    + ": Welcher Stein macht den Anfangszug?");
            if (gueltigeEingabe(stein, spieler)) {
                weite = setzeZug(stein, 1, true);
            }
        } while (weite == -1);
        System.out.println(output());
        // Ende Anfangszug

        // Beginn Folgezug
        if (weite > 0 && weite < 6) {

            int tmpweite = weite;
            weite = -1;
            do {
                stein = readInt("Spieler " + spieler
                        + ": Naechster Stein darf " + tmpweite + " Schritte weit gehen."
                        + "Welcher Stein macht den Folgezug?");
                if (gueltigeEingabe(stein, spieler)) {
                    weite = setzeZug(stein, tmpweite, true);
                }
            } while (weite == -1);
            System.out.println(output());
        } // Ende Folgezug

        // Beginn Bonuszug
        if (weite == 6) {

            int tmpspieler = spieler;
            spieler = bonus ? 0 : spieler;
            int richtung;
            boolean vorwaerts;
            /* ist die eingabe der richtung ungueltig
             beginnt die do-while Schleife von vorne */
            weite = -1;

            do {
                stein = readInt("Spieler " + tmpspieler
                        + ": Bonuszug. Naechster Stein darf einen Schritt vor oder zurück."
                        + "Welcher Stein macht den Bonuszug?");
                richtung = readInt("In welche Richtung soll der Stein gehen"
                        + (bonus ? " (aus Sicht des Spielers, dem der Stein gehört)?" : " ")
                        + "0: vorwaerts, 1: rueckwaerts");
                if (richtung != 0 && richtung != 1) {
                    continue;
                }
                vorwaerts = (richtung == 0);
                // gegnerische Steine, die Ziellinie erreicht haben, duerfen nicht bewegt werden
                // (nicht rueckwaerts laut Spielregeln; nicht vorwaerts da ungueltiger Zug)
                if (findeStein(stein)[0] == -1 && !gueltigeEingabe(stein, tmpspieler)) {
                    continue;
                }
                if (gueltigeEingabe(stein, spieler)) {
                    // Schrittweite des Bonuszugs ist immer 1
                    weite = setzeZug(stein, 1, vorwaerts);
                }
            } while (weite == -1);

            System.out.println(output());
        } // Ende Bonuszug
    }

    public static void main(String args[]) {

        initSpiel();
        System.out.println(output());
        int bonusregel = 2;
        while (bonusregel != 0 && bonusregel != 1) {
            bonusregel = readInt("Möchten Sie mit der optionalen Bonusregeln spielen?\n"
                    + "D.h. im Bonuszug darf auch ein Stein des Gegners eine Reihe vor- oder zurueckgesetzt werden.\n"
                    + "0: Ja, 1: Nein");
        }
        boolean bonus = false;
        if (bonusregel == 0) {
            bonus = true;
        }
        while (!spielende()) {
            spielerZieht(1, bonus);
            if (!spielende()) {
                spielerZieht(-1, bonus);
            }
        }
        zaehlePunkte();

//        // Spieler 1: 46 Punkte, Spieler -1: 39 Punkte
//        spielfeld = new int[][] {{-1,-2,-3,-4,-5,0},
//         {-6,-7,-8,0,0,0},
//         {-9,-10,-11,0,0,0},
//         {0,0,0,0,0,0},
//         {-12,0,0,0,0,0},
//         {1,2,0,0,0,0},
//         {3,4,5,6,0,0},
//         {7,8,9,10,11,12}};
//         System.out.println(output());
//         zaehlePunkte();
    }

}


