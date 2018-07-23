package pgdp;

public class Quadrat extends Grundflaeche implements Quadrierbar, Polygon {

    private final int laenge;

    public int getLaenge() {
        return laenge;
    }

    @Override
    public int getEckenAnzahl() {
        return 4;
    }

    @Override
    public double umfang() {
        return 4 * laenge;
    }

    @Override
    public double flaeche() {
        return laenge * laenge;
    }

    @Override
    public boolean istQuadrat() {
        return true;
    }

    @Override
    public Quadrat zuQuadrat() {
        return this;
    }

    public Quadrat(int laenge) {
        this.laenge = laenge;
    }

    @Override
    public String toString() {
        return "Quadrat{" + "laenge=" + laenge + super.toString() + ", ecken=" + getEckenAnzahl() + '}';
    }

}
