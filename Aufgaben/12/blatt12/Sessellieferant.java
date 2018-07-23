
package pgdp;

public class Sessellieferant implements Runnable {

    private Sesselfabrik sf;
    private int anzahl;

    public Sessellieferant(Sesselfabrik sf, int anzahl) {
        this.sf = sf;
        this.anzahl = anzahl;
    }
}
 
