package pgdp;

public class Grundflaeche {

    public double umfang() {
        return 0;
    }

    public double flaeche() {
        return 0;
    }

    public boolean istQuadrat() {
        return false;
    }

    public Quadrat zuQuadrat() {
        return null;
    }

    @Override
    public String toString() {
        return "Umfang: " + umfang() + "; Flaeche: " + flaeche() + "; Quadrat?: " + istQuadrat();
    }
}
