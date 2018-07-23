package pgdp;

public class Rechteck extends Grundflaeche implements Quadrierbar, Polygon {

    private final int breite;
    private final int laenge;

    public int getBreite() {
        return breite;
    }

    public int getLaenge() {
        return laenge;
    }

    @Override
    public int getEckenAnzahl() {
        return 4;
    }

    @Override
    public double umfang() {
        return 2 * breite + 2 * laenge;
    }

    @Override
    public double flaeche() {
        return breite * laenge;
    }

    public Rechteck(int breite, int laenge) {
        this.breite = breite;
        this.laenge = laenge;
    }

    @Override
    public boolean istQuadrat() {
        return laenge == breite;
    }

    @Override
    public Quadrat zuQuadrat() {
        if (istQuadrat()) {
            return new Quadrat(breite);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Rechteck{" + "breite=" + breite + ", laenge=" + laenge + super.toString() + ", ecken=" + getEckenAnzahl() + '}';
    }

}
